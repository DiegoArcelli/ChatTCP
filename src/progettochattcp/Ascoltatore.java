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
public class Ascoltatore extends Thread {
    
    private Host h;

    public Ascoltatore(Host h) {
        this.h = h;
    }
    
    public void run(){
        while(h.isOnline()==true){
            h.ricevi();
        }
    }
    
}