/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_ef;

import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author adria
 */
public class MBCP {
    private CopyOnWriteArrayList<mensaje> mensajes_creado = new CopyOnWriteArrayList<mensaje>();
    private CopyOnWriteArrayList<mensaje> mensajes_recibidos = new CopyOnWriteArrayList<mensaje>();
    private CopyOnWriteArrayList<mensaje> mensajes_espera = new CopyOnWriteArrayList<mensaje>();
    private CopyOnWriteArrayList<Integer> ci = new CopyOnWriteArrayList<Integer>();
    private CopyOnWriteArrayList<Integer> cic = new CopyOnWriteArrayList<Integer>();
    
private int[] vt = {0, 0, 0, 0, 0, 0};
private String datos;
private int k; //Número de proceso
private int tk = 1; //Número de mensaje
private static String ip = "";
private static int puerto = 0;
private DefaultListModel modelo;
private String v = "";
    public MBCP() {
        
    }
    
}
