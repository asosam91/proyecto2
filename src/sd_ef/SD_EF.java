/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        // TODO code application logic here
        int puerto=0;
        int numeroDeProceso;
        numeroDeProceso = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero de proceso para asignar un puerto \n"
                                                                        + "1 - 20001\n"  
                                                                        + "2 - 20002\n" 
                                                                        + "3 - 20003\n"
                                                                        + "4 - 20004\n"
                                                                        + "5 - 20005\n"
                                                                        + "6 - 20006"));
//      
        
        switch (numeroDeProceso) {
            case 1:
                puerto = 20001; // Puerto del Proceso 1
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
      
        i.setVisible(true);
        i.setPuerto(puerto);
        i.Recibir();
    }
    
}
