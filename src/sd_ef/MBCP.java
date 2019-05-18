/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_ef;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author adria
 */
public class MBCP {
    private ArrayList<Mensaje> mensajes_creado = new ArrayList<Mensaje>();
    private ArrayList<Mensaje> mensajes_recibidos = new ArrayList<Mensaje>();
    private ArrayList<Mensaje> mensajes_espera = new ArrayList<Mensaje>();
    private ArrayList<Integer> ci = new ArrayList<Integer>();
    private ArrayList<Integer> cic = new ArrayList<Integer>();
    
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
