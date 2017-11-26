package tests;

import api_comunicacao.*;
import lib.Debug;

public class Test1{
	public static void main(String[] args){
		String ip = "192.168.122.10";
		int porta = 5000;
		Debug.DEBUG = true;
		System.out.println("Servidor iniciado no endereco: "+ip+" porta: "+porta);
		try{

			APIComunicacao.ligarServidor(new ObjetoComunicacao(ip, porta, 10000){
				public String sucesso(String resultado){
					System.out.println("Recebida a requisição de "+this.getIpCliente());
					System.out.println(resultado);
					System.out.println("fim");
					return	null;
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