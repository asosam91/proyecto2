package sd_ef;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Lorrain
 */
public class Mensaje {
    private String textoMensaje;
    private int numeroDeProceso;
    private int numeroDeMensaje;
    private CopyOnWriteArrayList<Integer> HM = new CopyOnWriteArrayList();

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    public CopyOnWriteArrayList<Integer> getHM() {
        return HM;
    }

    public void setHM(CopyOnWriteArrayList<Integer> HM) {
        this.HM = HM;
    }

    public int getNumeroDeProceso() {
        return numeroDeProceso;
    }

    public void setNumeroDeProceso(int numeroDeProceso) {
        this.numeroDeProceso = numeroDeProceso;
    }

    public int getNumeroDeMensaje() {
        return numeroDeMensaje;
    }

    public void setNumeroDeMensaje(int numeroDeMensaje) {
        this.numeroDeMensaje = numeroDeMensaje;
    }

    public Mensaje(String textoMensaje, int numeroDeProceso, int numeroDeMensaje, CopyOnWriteArrayList<Integer> HM) {
        this.textoMensaje = textoMensaje;
        this.numeroDeProceso = numeroDeProceso;
        this.numeroDeMensaje = numeroDeMensaje;
        for(int variable: HM){
            this.HM.add(variable);
        }
    }

    public Mensaje(String mensaje) {
        String[] partes = mensaje.split("-");
        this.numeroDeProceso = Integer.parseInt(partes[0]);
        this.numeroDeMensaje = Integer.parseInt(partes[1]);
        this.textoMensaje = partes[2];
        for(int i = 3;i < partes.length; i++){
            this.HM.add(Integer.parseInt(partes[3]));
        }
    }

    @Override
    public String toString(){
        String HdeM = " ";
        for (int i = 0; i < HM.size(); i++) {
            HdeM = HdeM + "-" + HM.get(i);
        }
        String mensajeString = numeroDeProceso + "-" + numeroDeMensaje + "-" + textoMensaje;
        return mensajeString + HdeM;
    }
 
}
