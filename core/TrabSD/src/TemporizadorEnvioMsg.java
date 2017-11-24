import java.util.ArrayList;
import api_comunicacao.APIComunicacao;
import api_comunicacao.ObjetoComunicacao;

public class TemporizadorEnvioMsg implements Runnable {

	@Override
	public void run() {
		System.out.println("*****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" started*****");
		
		ControlShared cs = ControlShared.getInstance();
		
		while(!Thread.interrupted()){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// We've been interrupted: no more messages.
				System.out.println("++Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" interrompida++");
				return;
			}
			
			//Pega msgs para serem enviadas
			ArrayList<MensagemParaEnviar> listaMsgs;
			listaMsgs = MySqlCon.getMsgsToSend();
			
			//Solicita API para mandar msgs se Cliente online
			for(MensagemParaEnviar msg : listaMsgs){
				if(cs.idsAtivos.contains(msg.getIdClient())){
					try {
						ObjetoComunicacao oc = new ObjetoComunicacao(cs.localIP, 5001, msg.getClientEnd(), 4444, msg.getContent(), 500) {
							
							@Override
							public void sucesso(String resultado) {
								// Após enviar deve ter confimação de envio, caso positivo propagar para banco
								MySqlCon.confirmReceived(msg.getId());								
							}
							
							@Override
							public void fimEscuta() {}
							
							@Override
							public void erro(Exception e) {e.printStackTrace();}
							
						};APIComunicacao.enviar(oc);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			}			
			
		}
		
		System.out.println("****Thread: "+Thread.currentThread().getName()+" Class: "+this.getClass().getName()+" terminando****");
	}

}
