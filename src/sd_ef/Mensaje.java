/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_ef;

/**
 *
 * @author Lorrain
 */
public class Mensaje {
    private String textoMensaje;
    private int numeroDeProceso;
    private int numeroDeMensaje;

    public Mensaje(String textoMensaje, int numeroDeProceso, int numeroDeMensaje) {
        this.textoMensaje = textoMensaje;
        this.numeroDeProceso = numeroDeProceso;
        this.numeroDeMensaje = numeroDeMensaje;
        System.out.println(textoMensaje+" "+ numeroDeProceso+" "+ numeroDeMensaje);
    }
    
    @Override
    public String toString(){
        String mensajeString = numeroDeProceso + "-" + numeroDeMensaje + "-" + textoMensaje;
        return mensajeString;
        
    }
    
    
    
}