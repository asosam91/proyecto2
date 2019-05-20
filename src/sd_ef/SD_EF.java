package sd_ef;

import javax.swing.JOptionPane;

/**
 *
 * @author Lorrain
 */
public class SD_EF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int puerto=0;
        int numeroDeProceso;
        numeroDeProceso = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero de proceso que deseas ejecutar"));
        switch (numeroDeProceso) {
            case 1:
                puerto = 20001;
                break;
            case 2:
                puerto = 20002; 
                break;
            case 3:
                puerto = 20003;
                break;
            case 4:
                puerto = 20004;
                break;
            case 5:
                puerto = 20005;
                break;
            case 6:
                puerto = 20006;
                break;
        }
        Interfaz i = new Interfaz();
        i.setNumeroDeProceso(numeroDeProceso);
        i.setIdProceso(numeroDeProceso);
        i.setVisible(true);
        i.setPuerto(puerto);
        i.Recibir(puerto);
    }
}
