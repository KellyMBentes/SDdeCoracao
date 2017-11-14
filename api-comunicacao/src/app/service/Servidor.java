package app.service;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import app.conf.Configuracao;
import app.controller.Controle;
import api_comunicacao.RespostaServidor;
import app.model.Mensagem;
import app.model.Usuario;
import lib.Debug;

public abstract class Servidor {
  public void ligar(RespostaServidor callback) {
    try{   
      int porta = Integer.parseInt(Configuracao.get("porta"));
      ServerSocket servidor = new ServerSocket(porta);
      Debug.println("Porta "+porta+" aberta!");
      Debug.println("Endereço: "+servidor.getInetAddress().getHostAddress());
      Debug.println("Nome: "+servidor.getInetAddress().getHostName());

      Socket cliente = servidor.accept();
      Debug.println("Nova conexão com o cliente " +   
        cliente.getInetAddress().getHostAddress());

      String ip = cliente.getInetAddress().getHostAddress();
      String id = ler(cliente);

      Debug.println("Salvando usuário se não existir");

      new Controle().salvarUsuario(new Usuario(id, ip), false);

      Mensagem mensagem = new Mensagem(ler(cliente));
      // ler a mensagem e fazer alguma coisa com o conteúdo (aqui entra a parte do Lucas e do )
      callback.run(mensagem.toString());

      servidor.close();
    }catch(Exception e){
      Debug.erroln("Erro ao tentar enviar a mensagem!");
      e.printStackTrace();
    }

  }

  abstract void escrever(Socket cliente, Mensagem mensagem);
  abstract String ler(Socket cliente);
}