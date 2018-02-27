/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettochattcp;

/**
 *
 * @author diego
 */
public class ChatClient {
    
    public static void main(String[] args){
        
        Client c = new Client(2000,"Client","localhost");
        c.connetti();
        Ascoltatore a = new Ascoltatore(c);
        a.start();
        while(c.isOnline()==true){
            c.invia();
        }
        
    }
    
}
