/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettochattcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Server extends Host {
    
    protected ServerSocket sSocket;
    
    public Server(int port, String name) {
        super(port, name);
    }
    
    public void attendi(){ 
        try {
            System.out.println("In attesa di connessioni");
            sSocket = new ServerSocket(port);
            connection = sSocket.accept();
            System.out.println("Connessione stabilita");
            close = false;
            online = true;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void chiudi(){
        try {
            connection.close();
            sSocket.close();
        } catch (IOException ex) {
            System.err.println("Errore nella chiusura della connessione!");
        }
        System.out.println("Connessione chiusa!");
    }
    

    
}
