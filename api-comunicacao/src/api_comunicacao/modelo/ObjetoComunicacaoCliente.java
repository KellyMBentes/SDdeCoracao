package api_comunicacao.modelo;

public abstract class ObjetoComunicacaoCliente extends ObjetoComunicacao {
	public ObjetoComunicacaoCliente(String ip, int porta, String ipDest, int portaDest, String dado, int timeout) throws java.lang.Exception{
		super(ip, porta, ipDest, portaDest, dado, timeout);
	}

	public String sucesso(String resultado){return null;}
	public abstract void erro(Exception e);
	public abstract void fimEscuta();
} 