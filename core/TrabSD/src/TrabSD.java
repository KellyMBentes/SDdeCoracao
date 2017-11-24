import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class TrabSD {

	
	public static void main(String[] args) {
		System.out.println("*****Thread: Main started*****");
		
		// Cria Singleton do controle de variaveis compartilhadas
		ControlShared cs = ControlShared.getInstance();
		if(cs.equals(null))
			System.exit(0);
		
		
		//Cria 2 Threads pra escutar Publishers
		Thread listenPub1 = new Thread(new ListenPublisher());
		Thread listenPub2 = new Thread(new ListenPublisher());
		listenPub1.start();
		listenPub2.start();
		
		
		//Cria Thread que escuta novos clientes
		Thread listenSub = new Thread(new ListenSubscriber());
		listenSub.start();
		
		//Cria Thread que envia msgs de tempo em tempo
		Thread bgMsgSender = new Thread(new TemporizadorEnvioMsg());
		bgMsgSender.start();
		
		// Thread responsavel em manter lista de subscribers vivos
		Thread checkIdsAlive = new Thread(){
			public void run(){
				System.out.println("*****Thread: auxMain started*****");
				
				ControlShared cs = ControlShared.getInstance();
				
				while(!Thread.interrupted()){
					try {
						Thread.sleep(2490);
					} catch (InterruptedException e) {
						System.out.println("++Thread: auxMain interrompida++");
						return;
					}
					
					// Verifica se clientes estão vivos de tempo em tempo
					if(!cs.idsAtivos.isEmpty()){
						InetAddress iNet;
						ArrayList<Integer> toRemoveList = new ArrayList<Integer>();
						
						for(int id : cs.idsAtivos){
							for (Client cli : cs.listaClientes){
								if(id == cli.getId()){
									try {
										iNet = InetAddress.getByName(cli.getEndereco());
										if(!iNet.isReachable(500)){
											toRemoveList.add(id);
										}
											
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
						for(int ele : toRemoveList){
							cs.idsAtivos.remove((Object)ele);
						}
					}
				}
				System.out.println("****Thread: auxMain terminando****");
			}
		};checkIdsAlive.start();
		
		
		// Para execução apertando tecla "s"
		try (Scanner scanner = new Scanner(System.in)) {
			boolean keepWaiting = true;
            while(keepWaiting) {
                String userInput = scanner.next();
                if("s".equals(userInput)) {
                    // Interrompe Threads executando
                	if(listenPub1.isAlive()){
            			listenPub1.interrupt();
            		}
                	
                	if(listenPub2.isAlive()){
            			listenPub2.interrupt();
            		}
                	
                	if(listenSub.isAlive()){
            			listenSub.interrupt();
            		}
                	
                	if(bgMsgSender.isAlive()){
            			bgMsgSender.interrupt();
            		}
                	
                	if(checkIdsAlive.isAlive()){
            			checkIdsAlive.interrupt();
            		}
                	
                    keepWaiting = false;
                }
            }
        }
		
		System.out.println("****Thread: Main terminando****");
	
	}
	
}
