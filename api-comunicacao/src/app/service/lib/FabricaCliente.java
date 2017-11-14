package src.app.service.lib;

import src.app.service.Cliente;
import src.app.service.ClienteXML;

public class FabricaCliente {

	private static final String TIPO_XML = "xml";
	
	public static Cliente getCliente(String tipo){
		if(tipo == TIPO_XML){
			return new ClienteXML();
		}else{
			return null;
		}
	}
}