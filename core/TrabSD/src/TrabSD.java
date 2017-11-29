import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TrabSD {

	public static void main(String[] args) {
//		boolean resposta;
//		resposta = runSystemCommand("Ping -n 2 abacate.ic.uff.br", "abacate.ic.uff.br");
//		System.out.println("Resposta foi: "+ resposta);
//		resposta = runSystemCommand("Ping -n 2 10.1.29.125", "10.1.29.125");
//		System.out.println("Resposta foi: "+ resposta);
//		
//		System.exit(0);

		System.out.println("*****Thread: Main started*****");
		System.out.println("Digite 's' para encerrar.");
		
		// Cria Singleton do controle de variaveis compartilhadas
		ControlShared cs = ControlShared.getInstance();
		if(cs == null)
			System.exit(0);
		
		System.out.println("--O ip local eh: "+ cs.localIP);
		

		Thread[] bgThreads = new Thread[4]; // Background Threads holder
		
		//Cria Thread pra escutar Publishers
		bgThreads[0] = new Thread(new ListenPublisher());
		
		
		//Cria Thread que escuta novos clientes
		bgThreads[1] = new Thread(new ListenSubscriber());
		
		//Cria Thread que envia msgs de tempo em tempo
		bgThreads[2] = new Thread(new TemporizadorEnvioMsg());
		
		// Cria Thread responsavel em manter lista de subscribers vivos
		bgThreads[3] = new Thread(){
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
					
					// Verifica se clientes estao vivos de tempo em tempo
					if(!cs.idsAtivos.isEmpty()){
						ArrayList<Integer> toRemoveList = new ArrayList<Integer>();
						
						System.out.print("Lista ids ativos: {");
						for(int i : cs.idsAtivos){
							System.out.print(i+",");
						}
						System.out.println("}");
						
						ArrayList<Integer> tempAtivos = cs.idsAtivos;
						ArrayList<Client> tempClients = cs.listaClientes;
						
						for(int id : tempAtivos){
							for (Client cli : tempClients){
								if(id == cli.getId()){
									try {
										if(!runSystemCommand("Ping -n 2 "+cli.getEndereco(), cli.getEndereco())){
//											System.out.println("--Cliente id: "+cli.getId()+" não respondeu ping.");
											toRemoveList.add(id);
										}
//										else{System.out.println("--Ping deu certo pro id: "+cli.getId());}
											
									} catch (Exception e) {
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
		};
		
		// Inicia Threads
		for(Thread t : bgThreads){
			t.start();
		}
		
		// Para execucao apertando tecla "s"
		try (Scanner scanner = new Scanner(System.in)) {
			boolean keepWaiting = true;
            while(keepWaiting) {
                String userInput = scanner.next();
                if("s".equals(userInput)) {
                    // Interrompe Threads executando
                	for(Thread t : bgThreads){
	                	if(t.isAlive()){
	            			t.interrupt();
	            		}
                	}
                	
                    keepWaiting = false;
                }
            }
        }
		
		System.out.println("****Thread: Main terminando****");
		System.exit(0);
	}
	
	public static boolean runSystemCommand(String command, String ip) {
		boolean ans = true;
		
	    try {
	        Process p = Runtime.getRuntime().exec(command);
	        BufferedReader inputStream = new BufferedReader(
	                new InputStreamReader(p.getInputStream()));

	        String s = "";
	        // reading output stream of the command
	        while ((s = inputStream.readLine()) != null) {
//	            System.out.println(s);
	            if(s.equals("Request timed out.") || s.matches("Reply from(.*): Destination host unreachable.")){
	            	ans = false;
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ans;
	}
	
}
