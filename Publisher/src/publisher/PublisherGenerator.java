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

/**
 *
 * @author jc-am
 */
public class PublisherGenerator extends Thread implements Runnable {

    static boolean defaultTags[] = {true, true, true};

    public static void main(String[] args) throws InterruptedException, IOException {
        ServerSocket serverSocket0 = new ServerSocket(5000);
        
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
                p1 = new Publisher("127.0.0.1", 5000, t1);
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
}
