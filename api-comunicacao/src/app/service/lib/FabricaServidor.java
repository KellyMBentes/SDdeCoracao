package src.app.service.lib;

import src.app.service.Servidor;
import src.app.service.ServidorXML;

public class FabricaServidor {

	private static final String TIPO_XML = "xml";
	
	public static Servidor getServidor(String tipo){
		if(tipo == TIPO_XML){
			return new ServidorXML();
		}else{
			return null;
		}
	}
}