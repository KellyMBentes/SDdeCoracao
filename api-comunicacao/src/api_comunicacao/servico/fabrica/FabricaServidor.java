package api_comunicacao.servico.fabrica;

import api_comunicacao.servico.SocketServidor;

public class FabricaServidor {

	private static final String TIPO_XML = "xml";
	
	public static SocketServidor getServidor(String tipo){
		SocketServidor resultado = null;
		if(tipo == TIPO_XML){
			// return new ServidorXML();
		}else{
			resultado = new SocketServidor(){};
		}
		return resultado;
	}
}