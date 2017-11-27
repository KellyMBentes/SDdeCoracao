package api_comunicacao.servico;

import java.lang.Exception;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.net.*;

import api_comunicacao.lib.Debug;

public abstract class SocketCliente {
  private PrintStream saida;
  private Scanner entrada;

  public String enviar(String ip, int porta, String ipDest, int portaDest, String mensagem)
   throws InterruptedIOException, IOException, Exception {
    return this.enviar(ip, porta, ipDest, portaDest, mensagem, -1);
  }

  public String enviar(String ip, int porta, String ipDest, int portaDest, String mensagem, int timeout)
   throws InterruptedIOException, IOException, Exception {
    Socket cliente  = null;
    try {
      Debug.println("Cliente: iniciando");
      
      cliente = new Socket();
      cliente.setReuseAddress(true);
      if(timeout <= 0){
        cliente.setSoTimeout(timeout);
      }
      // if(!cliente.isBound()){
      Debug.println("Cliente: acessando através do endereço: "+ip+":"+porta);
      cliente.bind(new InetSocketAddress(ip, porta));
      Debug.println("Cliente: tentando conectar ao "+ipDest+": "+portaDest);
      cliente.connect(new InetSocketAddress(ipDest, portaDest));
      // }

      
      Debug.println("Cliente: enviando");
      escrever(cliente, mensagem);

      Debug.println("Cliente: esperando resultado");
      String resposta = ler(cliente);

      Debug.println("Cliente: resposta servidor: "+resposta);
      return resposta;
    } finally {
      if(this.entrada != null){this.entrada.close();}
      if(this.saida != null){this.saida.close();}
      if(cliente != null){
        Debug.println("Cliente: fechando");
        cliente.close();
        try { Thread.sleep(1000); } catch (InterruptedException ex) {}
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