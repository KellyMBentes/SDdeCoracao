package tests;

import api_comunicacao.*;
import lib.Debug;

public class Test2{
	public static void main(String[] args){
		String ip = "192.168.0.2";
		int porta = 5000;
		Debug.DEBUG = true;
		System.out.println("Cliente iniciado no endereco: "+ip+" porta: "+porta);
		try{

			APIComunicacao.enviar(new ObjetoComunicacao(ip, porta, "192.168.0.1", 5000, "Olá, sou o cliente com IP "+ip, 10000){
				public void sucesso(String resultado){
					System.out.println("Requisição enviada");
					System.out.println(resultado);
					System.out.println("fim");
				}
				public void erro(Exception e){
					System.out.println("Falha:"+e.getMessage());
				}
				public void fimEscuta(){
					System.out.println("Fim da escuta.");
					System.out.println("Nenhuma requisição foi estabelecida.");
				}
			});
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}