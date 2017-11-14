package lib;

public class Debug {
	private static final String PREFIXO = "DEBUG {:tipo} | {:msg}";
	public static boolean DEBUG = false;

	private static String getMensagem(String msg, String tipo){
		return (PREFIXO
			.replace("{:tipo}", tipo)
			.replace("{:msg}", msg));
	}
	
	public static void println(String msg){ 
		if(DEBUG) System.out.println(getMensagem(msg, ""));
	}

	public static void erroln(String msg){ 
		if(DEBUG) System.err.println(getMensagem(msg, "ERRO"));
	}
}