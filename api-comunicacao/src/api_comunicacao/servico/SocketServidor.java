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
  private PrintStream saida;
  private Scanner entrada;

  public void ligar(String  ip, int porta, ObjetoComunicacaoServidorString callback)
    throws InterruptedIOException, IOException, Exception {
    this.ligar(ip, porta, -1, callback);
  }
  public void ligar(String  ip, int porta, int timeout, ObjetoComunicacaoServidorString callback)
    throws InterruptedIOException, IOException, Exception {
      ServerSocket servidor = null;
      Socket cliente = null;
      try{

        Debug.println("Servidor iniciado.");
        servidor = new ServerSocket(porta, 2, InetAddress.getByName(ip));
        if(timeout <= 0){
          servidor.setSoTimeout(timeout);
        }

        while(true){
          Debug.println("Servidor: aguardando cliente.");
          cliente = servidor.accept();
          Debug.println("Servidor: cliente aceito.");

          // ler a mensagem e fazer alguma coisa com o conteúdo (aqui entra a parte do Lucas e do )
          Debug.println("Servidor: lendo mensagem.");
          String mensagem = ler(cliente);
          Debug.println("Servidor: chamando callback sucesso.");
          String resultado = callback.sucesso(mensagem, cliente);

          Debug.println("Servidor: enviando resultado: "+resultado+".");
          escrever(cliente, resultado);
          
          if(cliente != null){
            Debug.println("Servidor: fechando conexão com cliente.");
            cliente.close();
            Debug.println("Servidor: cliente desconectado.");
          }
        }
      } finally {
        if(this.entrada != null){this.entrada.close();}
        if(this.saida != null){this.saida.close();}
        if(servidor != null){
          servidor.close();
          Debug.println("Servidor: fechando.");
        }
      }
  }

  public void escrever(Socket cliente, String mensagem) throws IOException{
    this.saida = new PrintStream(cliente.getOutputStream());
    this.saida.println(mensagem);
    this.saida.flush();
  }

  public String ler(Socket cliente) throws IOException{
    String resultado = "";
    this.entrada = new Scanner(cliente.getInputStream());
    resultado += this.entrada.nextLine();
    return resultado;
  }
}