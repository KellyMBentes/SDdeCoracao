import api_comunicacao.APIComunicacao;
import api_comunicacao.modelo.*;

public class ListenPublisher implements Runnable{

	@Override
	public void run() {
		System.out.println("*****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" started*****");
		
		ControlShared cs = ControlShared.getInstance();
		
		// Chama metodo da API para escutar Publisher
		// Caso chegue uma mensagem instancia e salva a mensagem no BD
		try {
			ObjetoComunicacao oc = new ObjetoComunicacao(cs.localIP, 5000){
				public String sucesso(String resultado){
					System.out.println("--Publisher:: A string recebida foi: "+ resultado);
					System.out.println("--Publisher ip: "+this.getIpCliente());
					
					if(resultado != null){
						String[] msgSplitted = resultado.split(",");
						
						Message msg = new Message(Integer.parseInt(msgSplitted[0]), Integer.parseInt(msgSplitted[1]), Integer.parseInt(msgSplitted[2]), msgSplitted[3]);
						msg.saveInBD();
					}
					return "200";
				}

				public void erro(Exception e) {
					System.out.println("Deu erro na thread tipo ListenPublisher: "+Thread.currentThread().getName());
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}

				public void fimEscuta() {}
			};
			APIComunicacao.ligarServidor(oc);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" terminando****");
	}
	
}
