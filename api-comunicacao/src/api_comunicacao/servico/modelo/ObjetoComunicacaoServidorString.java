package api_comunicacao.servico.modelo;

import java.net.Socket;

public abstract class ObjetoComunicacaoServidorString {
	public abstract String sucesso(String val, Socket cliente);
}