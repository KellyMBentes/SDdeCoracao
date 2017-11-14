package app.service.lib;

import app.service.Servidor;
import app.service.ServidorXML;

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