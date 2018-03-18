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
        switch(mes){
            case "/manual":
                String man = "Lista comandi:\n/man: visualizzare tutti i comandi\n/author: modificare il nome utente\n/smile: inviare faccia sorridente\n/like: inviare pollice in su";
                testo = man;
                break;
            case "/author":
                mittente.impostaNome();
                break;
            case "/smile":
                testo = "\u263A";
                break;
            case "/like":
                testo = "\uD83D\uDC4D";
                break;
            case "/end":
                testo = "chiude la connessione";
                mittente.setClose(true);
                break;
            case "/offline":
                mittente.setOnline(false);
                testo = "non il linea";
                break;
            case "/online":
                mittente.setOnline(true);
                testo = "in linea";
                break;
            case "/echo":
                String s = "";
                String l = mittente.getLast();
                int c=0;
                for(int i=0;i<l.length();i++){
                    if(l.charAt(i)==':' && c==0){
                        s=l.substring(i+2, l.length());
                        c=1;
                    }
                }
                testo = s;
                break;
            default:
                testo = mes;
                break;
        }
        testo = testo;
    }
    
    public String getTesto() {
        return testo;
    }
    
    public String getName(){
        return mittente.getName();
    }
    
    
}
