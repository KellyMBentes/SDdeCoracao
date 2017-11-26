package servico;

import java.lang.Exception;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.net.*;

import lib.Debug;

public abstract class Cliente {
  public String enviar(String ip, int porta, String ipDest, int portaDest, String mensagem)
   throws InterruptedIOException, IOException, Exception {
    return this.enviar(ip, porta, ipDest, portaDest, mensagem, -1);
  }

  public String enviar(String ip, int porta, String ipDest, int portaDest, String mensagem, int timeout)
   throws InterruptedIOException, IOException, Exception {
    Socket cliente  = null;
    try {
      Debug.println("Cliente: iniciando");
      Debug.println("Cliente: tentando conectar ao "+ipDest+" na porta: "+portaDest);
      
      cliente = new Socket(InetAddress.getByName(ipDest), portaDest);
      if(timeout <= 0){
        cliente.setSoTimeout(timeout);
      }

      if(!cliente.isBound()){
        Debug.println("Cliente: binding");
        cliente.bind(new InetSocketAddress(ip, porta));
      }
      
      Debug.println("Cliente: enviando");
      escrever(cliente, mensagem);

      Debug.println("Cliente: esperando resultado");
      return ler(cliente);
    } finally {
      if(cliente != null){
        Debug.println("Cliente: fechando");
        cliente.close();
        try { Thread.sleep(500); } catch (InterruptedException ex) {}
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