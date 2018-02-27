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
public class ChatServer {
    
    public static void main(String[] args){
        
        Server s = new Server(2000,"Server");
        s.attendi();
        Ascoltatore a = new Ascoltatore(s);
        a.start();
        while(s.isOnline()==true){
            s.invia();
        }
        
    }

    
}
