package src.app.service;

import java.io.IOException;
import java.net.*;

import src.app.conf.Configuracao;
import src.app.model.Mensagem;
import src.app.model.Usuario;
import src.lib.Debug;

public abstract class Cliente {
  private static final String CONF_PORTA = "porta";

  public void enviar(Usuario usuario, Mensagem mensagem) 
          throws UnknownHostException, IOException {

    int porta = Integer.parseInt(Configuracao.get(CONF_PORTA));
    String ip = usuario.getIp();

    Socket cliente = new Socket(InetAddress.getByName(ip), porta);
    Debug.println("Conexão com "+ip+":"+porta+" estabelecida!");
    escrever(cliente, mensagem);

    Debug.println("Mensagem enviadas para "+ip+":"+porta);
    Debug.println("Conexão com "+ip+":"+porta+" fechada!");
  }

  abstract void escrever(Socket cliente, Mensagem mensagem);

  abstract String ler(Socket cliente);
}