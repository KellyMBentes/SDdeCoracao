//import java.util.ArrayList;

public class TrabSD {

	
	public static void main(String[] args) {
		ControlShared cs = ControlShared.getInstance();
		if(cs.equals(null))
			System.exit(0);
		
		
		//Cria 2 Threads pra escutar Publishers
//		Thread listenPub1 = new Thread(new ListenPublisher());
//		Thread listenPub2 = new Thread(new ListenPublisher());
//		listenPub1.start();
//		listenPub2.start();
		
		
		//Cria Thread que escuta novos clientes
//		Thread listenSub = new Thread(new ListenSubscriber());
//		listenSub.start();
		
		//Cria Thread que envia msgs de tempo em tempo
//		Thread bgMsgSender = new Thread(new TemporizadorEnvioMsg());
//		bgMsgSender.start();
		
		//Thread principal fica responsavel em manter lista de subscribers vivos
		// TODO verifica se clientes estão vivos de tempo em tempo
//		if(cs.idsAtivos.size() > 0){
//			for()
//		}
		
		simulacao1(cs);
	
	}
	
	private static void simulacao1(ControlShared cs){
		System.out.println("*****Main started*****");
		
		boolean[] bool1 = {true, false, false};
		boolean[] bool2 = {false, false, true};
		cs.listaClientes.add(new Client(1, bool1, "End 1"));
		cs.listaClientes.add(new Client(2, bool1, "End 2"));
		cs.listaClientes.add(new Client(3, bool2, "End 3"));
		
		for(Client c: cs.listaClientes){
			cs.idsAtivos.add(c.getId());
		}
		
		Thread bgThread = new Thread(new TemporizadorEnvioMsg());
		bgThread.start();
		
		try {
			System.out.println("**Main sleep**");
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			return;
		}
		
		//interrompe background thread
		if(bgThread.isAlive()){
			bgThread.interrupt();
		}
		System.out.println("****Main terminando****");
	}

}
