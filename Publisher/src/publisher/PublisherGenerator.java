/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publisher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import publisher.Conteudo;
import publisher.Publisher;
import publisher.Sleeper;

/**
 *
 * @author jc-am
 */
public class PublisherGenerator extends Thread implements Runnable {

    private static final String FORMATO_PARAMETRO = "-{:nome:}";
    static boolean defaultTags[] = {true, true, true};
    private static String ip = null;

    public static void main(String[] args) throws InterruptedException, IOException {
        PublisherGenerator.ip = PublisherGenerator.getParametro("ip", args);
        ServerSocket server = new ServerSocket(5000);

        PublisherGenerator p1 = new PublisherGenerator();
        PublisherGenerator p2 = new PublisherGenerator();
        Thread t = new Thread(p1);
        Thread u = new Thread(p2);
        t.start();
        u.start();
    }

    @Override
    public void run() {
        int t1;
        Conteudo c = new Conteudo();
        while (c.checkAvailability(Conteudo.chooseA, Conteudo.chooseB, Conteudo.chooseC)) {
            Publisher p1 = null;
            if(!c.checkAvailabilityOne(Conteudo.chooseA)){
                defaultTags[0] = false;
            }
            if(!c.checkAvailabilityOne(Conteudo.chooseB)){
                defaultTags[1] = false;
            }
            if(!c.checkAvailabilityOne(Conteudo.chooseC)){
                defaultTags[2] = false;
            }
            do {
                t1 = chooseTag();
                
            } while (!defaultTags[t1]);
            defaultTags[t1] = false;
            try {
                p1 = new Publisher(PublisherGenerator.ip, 5000, t1);
                p1.run();
            } catch (IOException ex) {
                Logger.getLogger(PublisherGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public static int chooseTag() {
        int i;
        do {
            i = Sleeper.random_int(0, 3);
        } while (!defaultTags[i]);

        return i;
    }

    private static String getParametro(String nomeParametro, String args[]){
        String resultado = null;
        String nomeParametroFormatado = FORMATO_PARAMETRO.replace("{:nome:}",nomeParametro);
        for (int i = 0; i < args.length; i++){
            if(args[i].equals(nomeParametroFormatado) && i+1 < args.length){
                resultado = args[i+1];
            }
        }
        return resultado;
    }
}
