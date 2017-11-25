package servico;

import java.lang.Exception;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.net.*;

import lib.Debug;

public abstract class Cliente {
  public void enviar(String ip, int porta, String ipDest, int portaDest, String mensagem, int timeout)
   throws InterruptedIOException, IOException, Exception {
    Debug.println("Cliente: iniciando");
    Debug.println("Cliente: tentando conectar à "+ipDest+" na porta: "+portaDest);
    Socket cliente = new Socket(InetAddress.getByName(ipDest), portaDest, InetAddress.getByName(ip), porta);
    cliente.setSoTimeout(timeout);
    Debug.println("Cliente: enviando");
    escrever(cliente, mensagem);
    Debug.println("Cliente: fechando");
    cliente.close();
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