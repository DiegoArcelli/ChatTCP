/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettochattcp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Client extends Host {
    
    protected String address;
    
    public Client(int port, String name, String address) {
        super(port, name);
        this.address = address;
    }
    
    public void connetti(){
        try {
            //apro la connessione con il server sulla porta specificata
            connection = new Socket(address,port);
            System.out.println("Connessione aperta");
            close = false;
            online = true;
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }
        catch(IOException e2){
            System.err.println(e2);
            e2.printStackTrace();
        }
    }
    
}
