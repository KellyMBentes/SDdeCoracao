package api_comunicacao.modelo;

import api_comunicacao.lib.IllegalArgumentIntException;
import api_comunicacao.lib.IllegalArgumentStrException;

public abstract class ObjetoComunicacao {
	// NN: not null ou not negative
	private static final boolean NN_IP =            true;
	private static final boolean NN_PORTA =         true;
	private static final boolean NN_IP_DEST =       false;
	private static final boolean NN_PORTA_DEST =    false;
	private static final boolean NN_IP_CLIENTE =    false;
	private static final boolean NN_PORTA_CLIENTE = false;
	private static final boolean NN_DADO =          false;
	private static final boolean NN_TIMEOUT =       true;
	
	private static final String CLASS_NAME =   "ObjetoComunicacao";
	private static final int TIMEOUT_DEFAULT = 5000;

	private String ip;
	private int porta;
	private String ipDest;
	private int portaDest;
	private String dado;
	private int timeout;
	private String ipCliente;
	private int portaCliente;
	public String resultado = null;

	public ObjetoComunicacao(String ip, int porta, String ipDest, int portaDest, String dado, int timeout) throws java.lang.Exception{
		this.setIp(ip);
		this.setPorta(porta);
		this.setIpDest(ipDest);
		this.setPortaDest(portaDest);
		this.setDado(dado);
		this.setTimeout(timeout);
	}

	public ObjetoComunicacao(String ip, int porta) throws java.lang.Exception{
		this(ip, porta, null, -1, null, -1);
	}

	public ObjetoComunicacao(String ip, int porta, int timeout) throws java.lang.Exception{
		this(ip, porta, null, -1, null, timeout);
	}

	public abstract String sucesso(String resultado);
	public abstract void erro(Exception e);
	public abstract void fimEscuta();

	private void setIp(String val) throws java.lang.Exception{
		if((val == null) && NN_IP){
			throw new IllegalArgumentStrException(CLASS_NAME, "ip");
		}
		this.ip = val;
	}

	private void setPorta(int val) throws java.lang.Exception{
		if((val < 0) && NN_PORTA){
			throw new IllegalArgumentIntException(CLASS_NAME, "porta");
		}
		this.porta = val;
	}

	private void setIpDest(String val) throws java.lang.Exception{
		if((val == null) && NN_IP_DEST){
			throw new IllegalArgumentStrException(CLASS_NAME, "ipDest");
		}
		this.ipDest = val;
	}

	private void setPortaDest(int val) throws java.lang.Exception{
		if((val < 0) && NN_PORTA_DEST){
			throw new IllegalArgumentIntException(CLASS_NAME, "portaDest");
		}
		this.portaDest = val;
	}

	public void setIpCliente(String val){
		this.ipCliente = val;
	}

	public void setPortaCliente(int val){
		this.portaCliente = val;
	}

	private void setDado(String val) throws java.lang.Exception{
		if((val == null) && NN_DADO){
			throw new IllegalArgumentStrException(CLASS_NAME, "dado");
		}
		this.dado = val;
	}

	private void setTimeout(int val) throws java.lang.Exception{
		if((val < 0) && NN_PORTA){
			val = TIMEOUT_DEFAULT;
		}
		this.timeout = val;
	}

	public String getIp(){ return this.ip; }
	public int getPorta(){ return this.porta; }
	public String getIpDest(){ return this.ipDest; }
	public int getPortaDest(){ return this.portaDest; }
	public String getIpCliente(){ return this.ipCliente; }
	public int getPortaCliente(){ return this.portaCliente; }
	public String getDado(){ return this.dado; }
	public int getTimeout(){ return this.timeout; }
}