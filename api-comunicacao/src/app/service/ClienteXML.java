package app.service;
import java.net.Socket;
import app.model.Mensagem;

public class ClienteXML extends Cliente {
	public void escrever(Socket cliente, Mensagem mensagem){
		System.out.println("ESCREVENDO XML");
	}
	public String ler(Socket cliente){
		String result = null;
		System.out.println("LENDO XML");
		return result;
	}
}