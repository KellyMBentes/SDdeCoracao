import api_comunicacao.APIComunicacao;
import api_comunicacao.modelo.*;

public class ListenSubscriber implements Runnable{

	
	@Override
	public void run() {
		System.out.println("*****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" started*****");
		
		ControlShared cs = ControlShared.getInstance();
		//Inicializa Clientes
		cs.listaClientes.clear();
		cs.listaClientes.addAll(MySqlCon.getClients());
		
		// Chama API para escutar subscriber
		try {
			
			ObjetoComunicacao oc = new ObjetoComunicacao(cs.localIP, 5003){
				public String sucesso(String resultado){
					Client subscriber = null;
					
					if(resultado == null)
						return "nulo";
					
					String[] msgSplitted = resultado.split("-");
					System.out.println("--Cliente:: A string recebida foi: "+ msgSplitted[1]);
					System.out.println("--Cliente ip: "+this.getIpCliente());
					
					msgSplitted = msgSplitted[1].split(",");
					// Se novo instancia e salva o cliente no BD add na listaClientes de ControlShared e nos idsAtivos
					if(msgSplitted[0].equals("n")){ // Se novo String e "n,a,b,c"
						subscriber = new Client(Integer.parseInt(msgSplitted[1]), Integer.parseInt(msgSplitted[2]), Integer.parseInt(msgSplitted[3]), this.getIpCliente());				
						
						try {
							subscriber.saveInBD();
							cs.listaClientes.add(subscriber);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("Erro ao salvar Cliente.");
							e.printStackTrace();
						}
					}
					else{// Se ja conhecido string eh "o,id"
						for (Client cli : cs.listaClientes){
							if(Integer.parseInt(msgSplitted[1]) == cli.getId())
								subscriber = cli;
						}
						
						if(!subscriber.getEndereco().equals(this.getIpCliente())){
							subscriber.setEndereco(this.getIpCliente());
							subscriber.updateEnd();
						}
					}
			 		// Coloca nos idsAtivos
					if (!cs.idsAtivos.contains(subscriber.getId()))
						cs.idsAtivos.add(subscriber.getId());
					
					return (subscriber.getId()+"");
				}

				public void erro(Exception e) {
					System.out.println("Deu erro na thread tipo ListenSubscriber: "+Thread.currentThread().getName());
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}

				public void fimEscuta() {}
				
			};APIComunicacao.ligarServidor(oc);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" terminando****");
	}
	
}
