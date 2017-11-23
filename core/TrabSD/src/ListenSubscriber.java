import api_comunicacao.APIComunicacao;
import api_comunicacao.ObjetoComunicacao;

public class ListenSubscriber implements Runnable{

	static int maxId = 1;
	
	@Override
	public void run() {
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

		// TODO Chama API para escutar subscriber
		try {
			
			ObjetoComunicacao oc = new ObjetoComunicacao(cs.localIP, 5001, 5000){
				@Override
				public void sucesso(String resultado){
					String[] msgSplitted = resultado.split(",");
					Client subscriber = null;
					
					// TODO Se novo instancia e salva o cliente no BD add na listaClientes de ControlShared e nos idsAtivos
					if(resultado.length() < 2){					
						subscriber = new Client(maxId, (Integer.parseInt(msgSplitted[0])!=0), (Integer.parseInt(msgSplitted[1])!=0), (Integer.parseInt(msgSplitted[2])!=0), this.getIpCliente());				
						
						// TODO como mandar msg no caso de sucesso?
						try {
							ObjetoComunicacao resposta = new ObjetoComunicacao(cs.localIP, 5001, subscriber.getEndereco(), 4444, String.format("%1$d", maxId), 500) {
								
								@Override
								public void sucesso(String arg0) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void fimEscuta() {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void erro(Exception arg0) {
									// TODO Auto-generated method stub
									
								}
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
							if(Integer.parseInt(msgSplitted[0]) == cli.getId())
								subscriber = cli;
						}
						
						if(!subscriber.getEndereco().equals(this.getIpCliente())){
							subscriber.setEndereco(this.getIpCliente());
							subscriber.updateEnd();
						}
					}
			 		// TODO Se não só coloca nos idsAtivos
					cs.idsAtivos.remove(subscriber.getId());
					cs.idsAtivos.add(subscriber.getId());
				}

				@Override
				public void erro(Exception arg0) {
					System.out.println("Deu erro na thread tipo ListenSubscriber: "+Thread.currentThread().getName());
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
	
}
