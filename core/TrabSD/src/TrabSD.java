import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import lib.Debug;

public class TrabSD {

	private static final String FORMATO_PARAMETRO = "-{:nome:}";

	public static void main(String[] args) {
//		ControlShared.getInstance().setLocalIp(getParametro("ipLocal", args));
		//Debug.DEBUG = getParametro("debug", args).trim().equals("true");

		System.out.println("*****Thread: Main started*****");
		
		// Cria Singleton do controle de variaveis compartilhadas
		ControlShared cs = ControlShared.getInstance();
		if(cs == null)
			System.exit(0);
		
		System.out.println("--O ip local eh: "+ cs.localIP);
		
//		Thread listenSub = new Thread(new ListenSubscriber());
		Thread msgSender = new Thread(new TemporizadorEnvioMsg());
//		listenSub.start();
		msgSender.start();

//		Thread[] bgThreads = new Thread[4]; // Background Threads holder
		
		//Cria Thread pra escutar Publishers
//		bgThreads[0] = new Thread(new ListenPublisher());
		
		
		//Cria Thread que escuta novos clientes
//		bgThreads[2] = new Thread(new ListenSubscriber());
		
		//Cria Thread que envia msgs de tempo em tempo
//		bgThreads[3] = new Thread(new TemporizadorEnvioMsg());
		
		// Cria Thread responsavel em manter lista de subscribers vivos
//		bgThreads[4] = new Thread(){
//		new Thread(){
//			public void run(){
//				System.out.println("*****Thread: auxMain started*****");
//				
//				ControlShared cs = ControlShared.getInstance();
//				
//				while(!Thread.interrupted()){
//					try {
//						Thread.sleep(2490);
//					} catch (InterruptedException e) {
//						System.out.println("++Thread: auxMain interrompida++");
//						return;
//					}
//					
//					// Verifica se clientes estao vivos de tempo em tempo
//					if(!cs.idsAtivos.isEmpty()){
//						InetAddress iNet;
//						ArrayList<Integer> toRemoveList = new ArrayList<Integer>();
//						
//						System.out.print("Lista ids ativos: {");
//						for(int i : cs.idsAtivos){
//							System.out.print(i+",");
//						}
//						System.out.println("}");
//						
//						for(int id : cs.idsAtivos){
//							for (Client cli : cs.listaClientes){
//								if(id == cli.getId()){
//									System.out.println("Entrei no if.");
//									try {
//										iNet = InetAddress.getByName(cli.getEndereco());
//										if(!iNet.isReachable(500)){
//											System.out.println("--Cliente id: "+cli.getId()+" não respondeu ping.");
//											toRemoveList.add(id);
//										}
//										else{System.out.println("--Ping deu certo pro id: "+cli.getId());}
//											
//									} catch (Exception e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//								}
//							}
//						}
//						for(int ele : toRemoveList){
//							cs.idsAtivos.remove((Object)ele);
//						}
//					}
//				}
//				System.out.println("****Thread: auxMain terminando****");
//			}
//		};
//		}.start();
		
		// Inicia Threads
//		for(Thread t : bgThreads){
//			t.start();
//		}
		
		// Para execucao apertando tecla "s"
		try (Scanner scanner = new Scanner(System.in)) {
			boolean keepWaiting = true;
            while(keepWaiting) {
                String userInput = scanner.next();
                if("s".equals(userInput)) {
                    // Interrompe Threads executando
//                	for(Thread t : bgThreads){
//	                	if(t.isAlive()){
//	            			t.interrupt();
//	            		}
//                	}
//                	listenSub.interrupt();
                	
                    keepWaiting = false;
                }
            }
        }
		
		System.out.println("****Thread: Main terminando****");
	
	}

	private static String getParametro(String nomeParametro, String args[]){
        String resultado = null;
        String nomeParametroFormatado = FORMATO_PARAMETRO.replace("{:nome:}",nomeParametro);
        for (int i = 0; i < args.length; i++){
            if(args[i].equals(nomeParametroFormatado) && i+1 < args.length){
                resultado = args[i+1];
            }
        }
        return resultado;
    }
	
}
