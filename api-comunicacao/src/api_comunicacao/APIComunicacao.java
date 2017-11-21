package api_comunicacao;

import java.lang.Exception;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.InetAddress;

import api_comunicacao.ObjetoComunicacao;
import lib.Debug;
import servico.lib.FabricaCliente;
import servico.lib.FabricaServidor;
import servico.Cliente;
import servico.Servidor;
import servico.lib.ObjetoComunicacaoServidorString;

public class APIComunicacao {
	private static final String tipo = "";

	public static void enviar(ObjetoComunicacao obj){
		try{
			Cliente cliente = FabricaCliente.getCliente(tipo);

			String ip = obj.getIp();
			int porta = obj.getPorta();
			String ipDest = obj.getIpDest();
			int portaDest = obj.getPortaDest();
			String dado = obj.getDado();
			int timeout = obj.getTimeout();

			cliente.enviar(ip, porta, ipDest, portaDest, dado, timeout);
		} catch (java.io.InterruptedIOException e) {
			obj.fimEscuta();
		} catch (Exception e) {
			obj.erro(e);
		}
	}

	public static void ligarServidor(ObjetoComunicacao obj){
		try {
			Servidor servidor = FabricaServidor.getServidor(tipo);

			String ip = obj.getIp();
			int porta = obj.getPorta();
			int timeout = obj.getTimeout();

			servidor.ligar(ip, porta, timeout, new ObjetoComunicacaoServidorString(){
				public void sucesso(String val, Socket cliente){
					String ip = cliente.getInetAddress().getHostAddress();
					int porta = cliente.getPort();

					Debug.println("Servidor: ip cliente: "+ip);
					Debug.println("Servidor: porta cliente: "+porta);

					obj.setIpCliente(ip);
					obj.setPortaCliente(porta);
					obj.sucesso(val);
				}
			});

		} catch (java.net.SocketTimeoutException e){
			obj.fimEscuta();
		} catch (Exception e){
			obj.erro(e);
		}
	}
}