package api_comunicacao;

import app.controller.Controle;
import app.conf.Configuracao;
import app.model.Usuario;
import app.model.Mensagem;

public class APIComunicacao {
	private static final String FLAG_ROOT = "--root";
	private static final String FLAG_REPOSITORIO = "-r";
	private Controle controle;
	private Configuracao configuracao;

	public APIComunicacao(String args[]){
		this.setParametrosConfiguracao(args);
		this.configuracao = Configuracao.getInstancia();
		this.controle = new Controle();
	}

	public void enviarMensagem(String idUsuario, String corpoMensagem){
		try {
			Usuario usuario = controle.buscarUsuarioPorId(idUsuario);
			Mensagem mensagem = new Mensagem(corpoMensagem);
			this.controle.enviarMensagem(usuario, mensagem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarUsuario(String id, String ip){
		salvarUsuario(id, ip, true);
	}
	public void salvarUsuario(String id, String ip, boolean forcar){
		try{
			this.controle.salvarUsuario(new Usuario(id, ip), forcar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ligarServidor(RespostaServidor callback){
		this.controle.ligarServidor(callback);
	}

	private void setParametrosConfiguracao(String args[]){
		String parametro = getParametro(args, FLAG_ROOT);
		if(parametro != null) Configuracao.ROOT = parametro;

		parametro = getParametro(args, FLAG_REPOSITORIO);
		if(parametro != null) Configuracao.REPOSITORIO = parametro;

		for(String chave: Configuracao.getInstancia().getChaves()){
			String chaveParametro = ("--"+chave);
			parametro = getParametro(args, chaveParametro);
			if(parametro != null) Configuracao.set(chave, parametro);
		}
	}

	private static String getParametro(String args[], String parametro){
		for(int i = 0; i < args.length; i++){
			if(args[i] == parametro && i+1 < args.length){
				return args[i+1];
			}
		}
		return null;
	}
}