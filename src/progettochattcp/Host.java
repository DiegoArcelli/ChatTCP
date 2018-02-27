/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettochattcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Host {
    
    protected int port;
    protected String name;
    protected DataInputStream in;
    protected DataOutputStream out;
    protected Socket connection;
    protected boolean online;
    
    public Host(int port, String name){
        this.port = port;
        this.name = name;
        online = false;
    }
    
    public void invia(){
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            out = new DataOutputStream(connection.getOutputStream());
            String mes = input.readLine();
            Messaggio m = new Messaggio(this,mes);
            mes = m.scomponi();
            out.writeUTF(mes);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ricevi(){
        try {
            in = new DataInputStream(connection.getInputStream());
            System.out.println("\u001B[31m" + in.readUTF() + "\u001B[0m");
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isOnline(){
        return online;
    }
    
    public String getName(){
        return name;
    }
    
}
