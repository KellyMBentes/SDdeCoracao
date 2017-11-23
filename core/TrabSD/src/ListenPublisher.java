import api_comunicacao.APIComunicacao;
import api_comunicacao.ObjetoComunicacao;

public class ListenPublisher implements Runnable{

	@Override
	public void run() {
		ControlShared cs = ControlShared.getInstance();
		
		// TODO Chama metodo da API para escutar Publisher
		// TODO Caso chegue uma mensagem instancia e salva a mensagem no BD
		
		try {
			ObjetoComunicacao oc = new ObjetoComunicacao(cs.localIP, 5000, 5000){
				@Override
				public void sucesso(String resultado){
					String[] msgSplitted = resultado.split(",");
					
					Message msg = new Message((Integer.parseInt(msgSplitted[0])!=0), (Integer.parseInt(msgSplitted[1])!=0), (Integer.parseInt(msgSplitted[2])!=0), msgSplitted[3]);
					msg.saveInBD();
				}

				@Override
				public void erro(Exception arg0) {
					// TODO Auto-generated method stub
					System.out.println("Deu erro na thread tipo ListenPublisher: "+Thread.currentThread().getName());
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
