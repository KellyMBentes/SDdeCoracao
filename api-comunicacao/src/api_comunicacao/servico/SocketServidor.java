package api_comunicacao.servico;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.net.*;
import java.util.Scanner;

import api_comunicacao.servico.modelo.ObjetoComunicacaoServidorString;
import api_comunicacao.lib.Debug;

public abstract class SocketServidor {
  public void ligar(String  ip, int porta, ObjetoComunicacaoServidorString callback)
    throws InterruptedIOException, IOException, Exception {
    this.ligar(ip, porta, -1, callback);
  }
  public void ligar(String  ip, int porta, int timeout, ObjetoComunicacaoServidorString callback)
    throws InterruptedIOException, IOException, Exception {
      ServerSocket servidor = null;
      Socket cliente = null;
      try{

        Debug.println("Servidor iniciado");
        servidor = new ServerSocket(porta, 2, InetAddress.getByName(ip));
        if(timeout <= 0){
          servidor.setSoTimeout(timeout);
        }

        while(true){
          Debug.println("Servidor: aguardando cliente");
          cliente = servidor.accept();
          Debug.println("Servidor: cliente aceito");

          // ler a mensagem e fazer alguma coisa com o conteúdo (aqui entra a parte do Lucas e do )
          Debug.println("Servidor: lendo mensagem");
          String mensagem = ler(cliente);
          Debug.println("Servidor: chamando callback");
          String resultado = callback.sucesso(mensagem, cliente);

          Debug.println("Servidor: enviando resultado.");
          escrever(cliente, resultado);
        }

      } finally {
        if(cliente != null){
          Debug.println("Servidor: fechando cliente");
          cliente.close();
        }
        if(servidor != null){
          servidor.close();
          Debug.println("Servidor: fechando");
        }
      }
  }

  public void escrever(Socket cliente, String mensagem) throws IOException{
    PrintStream saida = new PrintStream(cliente.getOutputStream());
    saida.println(mensagem);
    saida.close();
  }

  public String ler(Socket cliente) throws IOException{
    String resultado = "";
    Scanner entrada = new Scanner(cliente.getInputStream());
    while (entrada.hasNextLine()) {
      resultado += entrada.nextLine();
    }
    entrada.close();
    return resultado;
  }
}