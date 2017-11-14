package app.model;

import lib.IllegalArgumentStrException;
import lib.Debug;

public class Mensagem {

	private static final boolean NN_CORPO =  true;
	private static final String CLASS_NAME = "Mensagem";

	private String corpo;
	private String[] tags;

	public Mensagem(String corpo) throws java.lang.Exception{
		Debug.println("Criando Mensagem");
		this.setCorpo(corpo);
		Debug.println("Mensagem criada");
	}

	public void setCorpo(String val) throws java.lang.Exception{
		if((val == null) && NN_CORPO){
			throw new IllegalArgumentStrException(CLASS_NAME, "corpo");
		}
		this.corpo = val;
	}

	public String getCorpo(){ return this.corpo; }

	public String toString(){
		return ("{'corpo': {:corpo}}"
			.replace("{:corpo}", this.getCorpo())
		);
	}
}