package api_comunicacao;

import java.lang.Exception;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.InetAddress;

import api_comunicacao.modelo.ObjetoComunicacao;
import api_comunicacao.lib.Debug;
import api_comunicacao.servico.fabrica.FabricaCliente;
import api_comunicacao.servico.fabrica.FabricaServidor;
import api_comunicacao.servico.SocketCliente;
import api_comunicacao.servico.SocketServidor;
import api_comunicacao.servico.modelo.ObjetoComunicacaoServidorString;

public class APIComunicacao {
	private static final String tipo = "";

	public static void enviar(ObjetoComunicacao obj){
		try{
			SocketCliente cliente = FabricaCliente.getCliente(tipo);

			String ip = obj.getIp();
			int porta = obj.getPorta();
			String ipDest = obj.getIpDest();
			int portaDest = obj.getPortaDest();
			String dado = obj.getDado();
			int timeout = obj.getTimeout();

			obj.resultado = cliente.enviar(ip, porta, ipDest, portaDest, dado, timeout);
		} catch (java.io.InterruptedIOException e) {
			obj.fimEscuta();
		} catch (Exception e) {
			obj.erro(e);
		}
	}

	public static void ligarServidor(ObjetoComunicacao obj){
		try {
			SocketServidor servidor = FabricaServidor.getServidor(tipo);

			String ip = obj.getIp();
			int porta = obj.getPorta();
			int timeout = obj.getTimeout();

			servidor.ligar(ip, porta, timeout, new ObjetoComunicacaoServidorString(){
				public String sucesso(String val, Socket cliente){
					String ip = cliente.getInetAddress().getHostAddress();
					int porta = cliente.getPort();

					Debug.println("Servidor: ip cliente: "+ip);
					Debug.println("Servidor: porta cliente: "+porta);

					obj.setIpCliente(ip);
					obj.setPortaCliente(porta);
					return obj.sucesso(val);
				}
			});

		} catch (java.net.SocketTimeoutException e){
			obj.fimEscuta();
		} catch (Exception e){
			obj.erro(e);
		}
	}
}