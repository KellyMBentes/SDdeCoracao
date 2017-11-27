package api_comunicacao.servico.fabrica;

import api_comunicacao.servico.SocketCliente;

public class FabricaCliente {

	private static final String TIPO_XML = "xml";
	
	public static SocketCliente getCliente(String tipo){
		SocketCliente resultado = null;
		if(tipo == TIPO_XML){
			// return new ClienteXML();
		}else{
			resultado = new SocketCliente(){};
		}
		return resultado;
	}
}