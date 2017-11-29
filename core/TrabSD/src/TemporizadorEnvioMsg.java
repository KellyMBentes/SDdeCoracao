import java.util.ArrayList;
import api_comunicacao.APIComunicacao;
import api_comunicacao.modelo.*;

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
			ArrayList<MensagemParaEnviar> listaMsgs = new ArrayList<MensagemParaEnviar>();
			listaMsgs = MySqlCon.getMsgsToSend();
			
			//Solicita API para mandar msgs se Cliente online
			for(MensagemParaEnviar msg : listaMsgs){
				if(cs.idsAtivos.contains(msg.getIdClient())){
					System.out.println("Enviando msg: "+msg.getContent()+" -> para ip: "+msg.getClientEnd());
					try {
						ObjetoComunicacaoCliente occ = new ObjetoComunicacaoCliente(cs.localIP, 5005, msg.getClientEnd(), 8080, msg.getContent(), 500) {
							
							@Override
							public void fimEscuta() {}
							
							@Override
							public void erro(Exception e) {
								e.printStackTrace();
							}
						};
						APIComunicacao.enviar(occ);
						System.out.println("Resposta foi: "+occ.resultado);
						if(occ.resultado.equals("ok")){
							MySqlCon.confirmReceived(msg.getId());
							System.out.println("Mensagem recebida com sucesso no ip: "+msg.getClientEnd());
						}
						
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
