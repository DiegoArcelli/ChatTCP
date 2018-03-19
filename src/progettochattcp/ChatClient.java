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
        
        //istanzio il client ed effettuo la richiesta sulla porta 2000 del server
        Client c = new Client(2000,"Default","localhost");
        c.connetti();
        //istanzio il thread per ricevere i messaggi dal server
        Ascoltatore a = new Ascoltatore(c);
        a.start();
        //ciclo per inviare e ricevere i messaggi finchè la connesione non si chiude
        while(c.isClose()==false){
            c.invia();
        }
        c.chiudi();
        System.out.println("Connessione chiusa");
    }
    
}
