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
    
    public String scomponi(){
        String s;
        String parole[] = testo.split(" ");
        for(int i=0;i<parole.length;i++){
            if(parole[i].charAt(0)=='/'){
                s = this.interpreta(parole[i]);
                parole[i] = s;
            }
        }
        s="";
        for(int i=0;i<parole.length;i++){
            s+=parole[i] + " ";
        }
        testo = s;
        return testo;
    }
    
    public String interpreta(String mes){
        switch(mes){
            case "/autore":
                return mittente.getName();
            case "/smile":
                return ":-)";
            default:
                return mes;
        }
    }
    
}
