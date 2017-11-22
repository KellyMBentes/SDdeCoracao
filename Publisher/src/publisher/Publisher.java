package publisher;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static publisher.PublisherGenerator.defaultTags;

public class Publisher implements Runnable {

    Socket socket;
    DataOutputStream os;
    DataInputStream is;

    int tag;

    public Publisher(String ip, int port, int tag) throws IOException {

        this.socket = new Socket(ip, port);
        this.os = new DataOutputStream(socket.getOutputStream());
        this.tag = tag;
        this.is = new DataInputStream(socket.getInputStream());

    }
// declaration section:
// smtpClient: our client socket
// os: output stream// declaration section:
// smtpClient: our client socket
// os: output stream

// Initialization section:
// Try to open a socket on port 3223
// Try to open input and output streams
//    public void publish() throws IOException {
//     }
    public Socket getSocket() {
        return this.socket;
    }

    public DataOutputStream getDataOut() {
        return this.os;
    }

    @Override
    public void run() {
        
        Conteudo cont = new Conteudo();
        String A = "";
        String B = "";
        String C = "";
        if (this.tag == 0){
            A = "1";
            B = "0";
            C = "0";
            cont.setText(cont.getConteudoA());
        }
        else if(this.tag == 1){
            A = "0";
            B = "1";
            C = "0";
            cont.setText(cont.getConteudoB());
        }
        else if (this.tag == 2){
            A = "0";
            B = "0";
            C = "1";
            cont.setText(cont.getConteudoC());
        }
        String message = A + "," + B + "," + C + "," + cont.getText();
        try {
            os.writeBytes(message);
            os.flush();
            System.out.println(message);
        } catch (IOException ex) {
            System.out.println("deu ruim");
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        PublisherGenerator.defaultTags[this.tag] = true;   
        Sleeper.sleep_thread();
        PublisherGenerator.defaultTags[this.tag] = true;
    }

}
