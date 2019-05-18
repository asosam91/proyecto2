/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_ef;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorrain
 */
public class Comunicacion {
private String mensaje;
    public Comunicacion(String mensaje) {
        this.mensaje=mensaje;
    }
    public Comunicacion ()
    {
        
    }

    public void Enviar(String ip, int puerto) {
        System.out.println("Estoy enviando esto: " + mensaje);
        try {
            DatagramSocket aSocket = new DatagramSocket();
            // byte[] m = args[0].getBytes();
            String message = this.mensaje;
            byte[] m = message.getBytes();
            InetAddress aHost = InetAddress.getByName(ip); // <--- Mi IP
            int serverPort = puerto;
            DatagramPacket request = new DatagramPacket(m, message.length(), aHost, serverPort);
            aSocket.send(request);
            aSocket.close();
        } catch (SocketException ex) {
            Logger.getLogger(Comunicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Comunicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Comunicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Recibir(int puerto){
        System.out.println("Estoy escuchando por el puerto: " + puerto);
     while (true) {
            String n = "";
            try {
                DatagramSocket aSocket = new DatagramSocket(puerto);
                byte[] buffer = new byte[1000];
                while (true) {
                    DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                    aSocket.receive(request);
                    n = new String(request.getData());
                    System.out.println(n);
                }
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }
    
}
}