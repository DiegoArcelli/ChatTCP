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
public class ChatClient {
    
    public static void main(String[] args){
        
        Client c = new Client(2000,"Default","localhost");
        c.connetti();
        Ascoltatore a = new Ascoltatore(c);
        a.start();
        while(c.isClose()==false){
            c.invia();
        }
        try {
            a.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connessione chiusa");
    }
    
}
