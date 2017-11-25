package app.controller;

import app.model.Usuario;
import app.model.Mensagem;
import app.conf.Configuracao;
import app.dao.UsuarioCSVDao;
import app.service.Cliente;
import app.service.Servidor;
import app.service.lib.FabricaCliente;
import app.service.lib.FabricaServidor;
import api_comunicacao.RespostaServidor;
import lib.Debug;

public class Controle { 

	private static final String CONF_ESTRUTURA_COMUNICACAO = "estrutura_comunicacao_socket";

	public void enviarMensagem(Usuario usuario, Mensagem mensagem){
		Debug.println("Enviando Mensagem");
		if(usuario == null) {Debug.erroln("É necessário informar um usuário!"); return;}
		if(mensagem == null) {Debug.erroln("É necessário informar uma mensagem!"); return;}

		Cliente cliente = FabricaCliente.getCliente(Configuracao.get(CONF_ESTRUTURA_COMUNICACAO));
		if(cliente == null) {Debug.erroln("O padrão de comunicação está incorreto!"); return;}
		try{
			cliente.enviar(usuario, mensagem);
			Debug.println("Mensagem enviada!");
		} catch (Exception e) {
			Debug.erroln("Erro ao tentar enviar a mensagem!");
			e.printStackTrace();
		}
	}

	public void ligarServidor(RespostaServidor callback){
		Debug.println("Aguardando contato do usuário");
		Servidor servidor = FabricaServidor.getServidor(Configuracao.get(CONF_ESTRUTURA_COMUNICACAO));
		if(servidor == null) {Debug.erroln("O padrão de comunicação está incorreto!"); return;}
		servidor.ligar(callback);
	}

	public void salvarUsuario(Usuario usuario, boolean forcar){
		if(forcar || new UsuarioCSVDao().buscarUsuarioPorId(usuario.getId()) == null)
			new UsuarioCSVDao().salvarUsuario(usuario);
	}

	public void salvarUsuario(Usuario usuario){
		salvarUsuario(usuario, true);
	}

	public Usuario buscarUsuarioPorId(String idUsuario){
		return new UsuarioCSVDao().buscarUsuarioPorId(idUsuario);
	}
}