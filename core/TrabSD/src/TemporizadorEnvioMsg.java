import java.util.ArrayList;

public class TemporizadorEnvioMsg implements Runnable {
//	volatile ArrayList<Integer> lista;
//	
//	public TemporizadorEnvioMsg(ArrayList<Integer> l) {
//		this.lista = l;
//	}
	
	@Override
	public void run() {
		ControlShared cs = ControlShared.getInstance();
		
		System.out.println("*****Thread2 started*****");
		for(int i = 0; i <= 2; i++){
//			System.out.println("*Lista de clientes: ");
//			for(Cliente item: lista){
//				System.out.println("--Cliente => " + item.toString());
//			}
//			System.out.println();
			//Pausa por 5 segundos
			try {
				System.out.println("**Thread2 sleep**");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// We've been interrupted: no more messages.
				System.out.println("++Thread2 interrompida++");
				return;
			}
			
			//Pega msgs para serem enviadas
			ArrayList<MensagemParaEnviar> listaMsgs;
			listaMsgs = MySqlCon.getMsgsToSend();
			
			System.out.println("**Thread2 Enviando msgs**");
			//Solicita API para mandar msgs se Cliente online TODO
			for(MensagemParaEnviar msg : listaMsgs){
				if(cs.idsAtivos.contains(msg.getIdClient())){					
					System.out.println(msg.toString());
					// TODO após enviar deve ter confimação de envio, caso positivo propagar para banco
					//if()
					//	MySqlCon.confirmReceived(msg.getId());
				}
			}			
			
		}
		
		System.out.println("****Thread2 terminando****");
	}

}
