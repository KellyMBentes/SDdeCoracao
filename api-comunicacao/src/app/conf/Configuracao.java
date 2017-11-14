package src.app.conf;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import java.util.List;
import java.util.ArrayList;

import src.lib.JSONDAO;
import src.lib.RelativePath;
import src.lib.Debug;

public class Configuracao {
	
	public static String REPOSITORIO = "conf/conf.json";
	public static String ROOT = "api-comunicacao/";
	private static Configuracao instancia = new Configuracao();

	public static Configuracao getInstancia(){
		if(instancia == null){
			instancia = new Configuracao();
		}
		return instancia;
	}

	public static String get(String chave){
		String result = null;
		try {
			result = (String) (Configuracao.getInstancia()).getConfiguracao().get(chave);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void set(String chave, String valor){
		try {
			//(Configuracao.getInstancia()).getConfiguracao().set(chave, valor);
			Debug.println("Salvando configuração");
			// salvar?
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONObject configuracao;

	public Configuracao(){
		RelativePath.ROOT = Configuracao.ROOT;
		this.carregarConfiguracao();
		Debug.DEBUG = this.instancia.get("debug") == "true";
	}

	public List<String> getChaves(){
		return new ArrayList<String>(this.configuracao.keySet());
	}

	private void carregarConfiguracao(){
		try{
			JSONDAO dao = new JSONDAO(RelativePath.getPath(REPOSITORIO));
			this.configuracao = dao.read();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONObject getConfiguracao(){
		return this.configuracao;
	}

}