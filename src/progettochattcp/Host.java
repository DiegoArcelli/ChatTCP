/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettochattcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Host {
    
    protected int port; //numero della porta
    protected String name; //nome utente dell'host
    protected DataInputStream in; //stream di input
    protected DataOutputStream out; //stream di output
    protected FileInputStream inFile; //
    protected FileOutputStream outFile;
    protected Socket connection; //data socket
    protected boolean online;//stato dell'host
    protected String last;
    protected byte buffer[];
    static protected boolean close; //stato connessione
    
    public Host(int port, String name){
        this.port = port;
        this.name = name;
        online = false;
    }
    
    
    //metodo per invio di un messaggio
    public void invia(){
        //stream di caratteri per acquisire dati da tastiera
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            //acquisizione dello stream di output della connessione
            out = new DataOutputStream(connection.getOutputStream());
            //ciclo che si ripete finchè la connessione non viene chiusa
            while(close==false){
                //acquisizione da tastiera della stringa da inviare
                String mes = input.readLine();
                //creazione di un oggetto per gestire la stringa
                Messaggio m = new Messaggio(this,mes);
                //verifica della stringa in caso contenga messaggi speciali
                m.interpretaTesto(mes);
                //dopo aver elaborato la stinga estraggo il testo da inviare
                mes = m.getTesto();
                //se l'host è online il messaggio viene inviato altrimenti no
                if(online==true){ 
                    out.writeUTF(m.getName() + ": " + m.getTesto());
                    out.flush();
                    if(mes.equals("/file")==true){
                        System.out.print("Inserisci nome file: ");
                        String nome = input.readLine();
                        this.inviaFile(nome);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //metodo per ricevere i messaggi
    public void ricevi(){   
        try {
            //ciclo che si ripete finchè non si chiude la connesione
            while(close==false){
                //acquisizione dello stream di input
                in = new DataInputStream(connection.getInputStream());
                //leggo il messaggio inviato
                String m = in.readUTF();
                String f[] = m.split(":");
                if(f[1].equals(" /file")){
                    this.riceviFile("out.txt");
                    System.out.println("File ricevuto");
                }
                //se l'host è online il messaggio acquisito viene mostrato a schermo
                if(online==true){
                    System.out.println("\u001B[31m" + m + "\u001B[0m");
                    //memorizzazione dell'ultimo messaggio ricevuto
                    last = m;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inviaFile(String nome){
        try {
            out = new DataOutputStream(connection.getOutputStream());
            inFile = new FileInputStream(nome);
            buffer = new byte[4096];
            while (inFile.read(buffer) > 0) {
                out.write(buffer);
            }
        }
        catch (FileNotFoundException e){
            System.err.println("File non trovato");
        }
        catch (IOException e1) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, e1);
        }
    }
    
    public void riceviFile(String nome){
        try {
            in = new DataInputStream(connection.getInputStream());
            outFile = new FileOutputStream(nome);
            buffer = new byte[4096];
            int filesize = 15123;
            int read = 0;
            int totalRead = 0;
            int remaining = filesize;
            while((read = in.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                outFile.write(buffer, 0, read);
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chiudi(){
        try {
            if (connection!=null){
                //chiudo la conessione
                connection.close();
                System.out.println("Connessione chiusa!");
            }
        } catch(IOException e){
            System.err.println("Errore nella chiusura della connessione!");
        }
    }
    
    public void impostaNome(){
        //inserimento da tastiera del nuovo nome utente
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserisi nome: ");
        try {
            //assegno il nuovo nome utente
            this.name = input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isOnline(){
        return online;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    
    public String getName(){
        return name;
    }

    public String getLast() {
        return last;
    }

    public boolean isClose() {
        return close;
    }
    
}
