package tests;

import api_comunicacao.*;
import api_comunicacao.modelo.*;
import api_comunicacao.lib.Debug;

public class Test2{
	public static void main(String[] args){
		String ip = "192.168.122.11";
		String ipServidor = "192.168.122.10";
		int porta = 5000;
		int portaServidor = 5000;
		Debug.DEBUG = true;
		System.out.println("Cliente iniciado no endereco: "+ip+" porta: "+porta);
		try{
			ObjetoComunicacaoCliente occ = new ObjetoComunicacaoCliente(ip, porta, ipServidor, portaServidor, "Olá, sou o cliente com IP "+ip, 10000){
				public void erro(Exception e){
					System.out.println("Falha:"+e.getMessage());
				}
				public void fimEscuta(){
					System.out.println("Fim da escuta.");
					System.out.println("Nenhuma requisição foi estabelecida.");
				}
			};
			APIComunicacao.enviar(occ);
			APIComunicacao.enviar(occ);

		} catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}