package servico;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.net.*;
import java.util.Scanner;

import servico.lib.ObjetoComunicacaoServidorString;
import lib.Debug;

public abstract class Servidor {
  public void ligar(String  ip, int porta, int timeout, ObjetoComunicacaoServidorString callback)
    throws InterruptedIOException, IOException, Exception {
      ServerSocket servidor = null;
      Socket cliente = null;
      try{

        Debug.println("Servidor iniciado");
        servidor = new ServerSocket(porta, 1, InetAddress.getByName(ip));
        servidor.setSoTimeout(timeout);

        while(true){
          Debug.println("Servidor: aguardando cliente");
          cliente = servidor.accept();
          Debug.println("Servidor: cliente aceito");

          // ler a mensagem e fazer alguma coisa com o conteúdo (aqui entra a parte do Lucas e do )
          Debug.println("Servidor: lendo mensagem");
          String mensagem = ler(cliente);
          Debug.println("Servidor: chamando callback");
          callback.sucesso(mensagem, cliente);
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