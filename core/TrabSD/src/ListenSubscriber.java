import api_comunicacao.APIComunicacao;
import api_comunicacao.ObjetoComunicacao;

public class ListenSubscriber implements Runnable{

	static int maxId = 1;
	
	@Override
	public void run() {
		System.out.println("*****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" started*****");
		
		ControlShared cs = ControlShared.getInstance();
		//Inicializa Clientes
		cs.listaClientes.clear();
		cs.listaClientes.addAll(MySqlCon.getClients());
		
		if(cs.listaClientes.size() > 0){
			for (Client c : cs.listaClientes){
				int tempId = c.getId();
				if(maxId < tempId){
					maxId = tempId;
				}
			}
		}

		while(cs.keepRunning){
			// TODO Chama API para escutar subscriber
			try {
				
				ObjetoComunicacao oc = new ObjetoComunicacao(cs.localIP, 5001, 5000){
					@Override
					public void sucesso(String resultado){
						Client subscriber = null;
						
						// TODO Se novo instancia e salva o cliente no BD add na listaClientes de ControlShared e nos idsAtivos
						if(resultado.length() < 3){			
							String[] msgSplitted = resultado.split(",");
							subscriber = new Client(maxId, (Integer.parseInt(msgSplitted[0])!=0), (Integer.parseInt(msgSplitted[1])!=0), (Integer.parseInt(msgSplitted[2])!=0), this.getIpCliente());				
							
							// TODO como mandar msg no caso de sucesso?
							try {
								ObjetoComunicacao resposta = new ObjetoComunicacao(cs.localIP, 5001, subscriber.getEndereco(), 4444, String.format("%1$d", maxId), 500) {
									
									@Override
									public void sucesso(String arg0) {}
									
									@Override
									public void fimEscuta() {}
									
									@Override
									public void erro(Exception e) {e.printStackTrace();}
									
								};APIComunicacao.enviar(resposta);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							subscriber.saveInBD();
							maxId++;
						}
						else{
							for (Client cli : cs.listaClientes){
								if(Integer.parseInt(resultado) == cli.getId())
									subscriber = cli;
							}
							
							if(!subscriber.getEndereco().equals(this.getIpCliente())){
								subscriber.setEndereco(this.getIpCliente());
								subscriber.updateEnd();
							}
						}
				 		// TODO Se não só coloca nos idsAtivos
						if (!cs.idsAtivos.contains(subscriber.getId()))
							cs.idsAtivos.add(subscriber.getId());
					}
	
					@Override
					public void erro(Exception e) {
						System.out.println("Deu erro na thread tipo ListenSubscriber: "+Thread.currentThread().getName());
						e.printStackTrace();
					}
	
					@Override
					public void fimEscuta() {
						// TODO Auto-generated method stub
						
					}
				};APIComunicacao.ligarServidor(oc);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" terminando****");
	}
	
}
