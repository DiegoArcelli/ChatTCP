/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettochattcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ChatServer {
    
    public static void main(String[] args){
        
        Server s = new Server(2000,"Default");
        s.attendi();
        Ascoltatore a = new Ascoltatore(s);
        a.start();
        while(s.isClose()==false){
            s.invia();
        }
        try {
            a.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connessione chiusa");
    }

    
}
