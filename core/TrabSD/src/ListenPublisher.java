import api_comunicacao.APIComunicacao;
import api_comunicacao.ObjetoComunicacao;

public class ListenPublisher implements Runnable{

	@Override
	public void run() {
		System.out.println("*****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" started*****");
		
		ControlShared cs = ControlShared.getInstance();
		
		while(!Thread.interrupted()){
			// Chama metodo da API para escutar Publisher
			// Caso chegue uma mensagem instancia e salva a mensagem no BD
			try {
				ObjetoComunicacao oc = new ObjetoComunicacao(cs.localIP, 5000, 5000){
					@Override
					public void sucesso(String resultado){
						String[] msgSplitted = resultado.split(",");
						
						Message msg = new Message((Integer.parseInt(msgSplitted[0])!=0), (Integer.parseInt(msgSplitted[1])!=0), (Integer.parseInt(msgSplitted[2])!=0), msgSplitted[3]);
						msg.saveInBD();
					}
	
					@Override
					public void erro(Exception e) {
						System.out.println("Deu erro na thread tipo ListenPublisher: "+Thread.currentThread().getName());
						e.printStackTrace();
					}
	
					@Override
					public void fimEscuta() {}
				};
				APIComunicacao.ligarServidor(oc);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" terminando****");
	}
	
}
