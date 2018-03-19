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
        
        //instanzio il server e lo metto in ascolto sulla porta 2000
        Server s = new Server(2000,"Default");
        s.attendi();
        //istanzio il thread per ricevere i messaggi dal client
        Ascoltatore a = new Ascoltatore(s);
        a.start();
        //ciclo per inviare e ricevere i messaggi finchè la connesione non si chiude
        while(s.isClose()==false){
            s.invia();
        }
        s.chiudi();
        System.out.println("Connessione chiusa");
    }

    
}
