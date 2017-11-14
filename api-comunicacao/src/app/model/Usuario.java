package app.model;

import lib.IllegalArgumentStrException;
import lib.Debug;

public class Usuario {

	private static final boolean NN_ID =     true;
	private static final boolean NN_IP =     true;
	private static final String CLASS_NAME = "Usuario";

	private String id;
	private String ip;


	public Usuario(String id, String ip) throws java.lang.Exception{
		Debug.println("Criando usuário");
		this.setId(id);
		this.setIp(ip);
		Debug.println("Usuário criado");
	}

	public void setId(String val) throws java.lang.Exception{
		if((val == null) && NN_ID){
			throw new IllegalArgumentStrException(CLASS_NAME, "id");
		}
		this.id = val;
	}

	public void setIp(String val) throws java.lang.Exception{
		if((val == null) && NN_IP){
			throw new IllegalArgumentStrException(CLASS_NAME, "ip");
		}
		this.ip = val;
	}

	public String getId(){ return this.id; }
	public String getIp(){ return this.ip; }

	public String toString(){
		return ("{'id': {:id}, 'ip': {:id}}"
			.replace("{:id}", this.getId())
			.replace("{:ip}", this.getIp())
		);
	}
}