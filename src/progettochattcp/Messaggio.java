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
public class Messaggio {
    
    private Host mittente;
    private String testo;

    public Messaggio(Host mittente, String testo) {
        this.mittente = mittente;
        this.testo = testo;
    }
    
    public void interpretaTesto(String mes){
        //verifico quale comando è stato richiesto
        switch(mes){
            case "/manual":
                //stampo sullo standard output la lista di tutti i comandi
                System.out.println("Lista comandi:\n/manual: visualizzare tutti i comandi\n/author: modificare il nome utente\n/smile: inviare faccia sorridente\n/like: inviare pollice in su\n/offline: per andare offline\n/online: per tornare online\n/end: per chiudere la connessione\n/echo: per inviare l'ultimo messaggio ricevuto");
                break;
            case "/author":
                //richiamo il metodo per modificare il nome utente
                mittente.impostaNome();
                break;
            case "/smile":
                //invio una faccia sorridente
                testo = "\u263A";
                break;
            case "/like":
                //invio un pollice in suo
                testo = "\uD83D\uDC4D";
                break;
            case "/end":
                //chiusura della conessione
                testo = "chiude la connessione";
                mittente.setClose(true);
                break;
            case "/offline":
                //imposto l'host mittente offline
                mittente.setOnline(false);
                testo = "non il linea";
                break;
            case "/online":
                //imposto l'host mittente online
                mittente.setOnline(true);
                testo = "in linea";
                break;
            case "/echo":
                //rimuovo dall'ultimo messaggio ricevuto il nome del mittente
                String s = "";
                String l = mittente.getLast();
                int c=0;
                for(int i=0;i<l.length();i++){
                    if(l.charAt(i)==':' && c==0){
                        s=l.substring(i+2, l.length());
                        c=1;
                    }
                }
                //invio l'ultimo messaggio ricevuto
                testo = s;
                break;
            default:
                //lascio il messaggio invariato
                testo = mes;
                break;
        }
        //testo = testo;
    }
    
    public String getTesto() {
        return testo;
    }
    
    public String getName(){
        return mittente.getName();
    }
    
    
}
