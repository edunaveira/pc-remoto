/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pc.remoto;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author enaveira
 */
public class SocketServidor extends Thread{
    
    private ServerSocket servidor;//TODO: NO SE USA SUBSTITUIRLO POR EL SOCKET DATAGRAM
    
    private static final int TAMAÑO_MAXIMO_PAQUETE = 998;//bytes
    private static final int PUERTO_POR_DEFECTO = 35550;
    private ArrayList<Dispositivo> dispositivos = new ArrayList<Dispositivo>();  
    
    private boolean conectado;
    private Accion accion;

    private int puerto;
    
    public SocketServidor(int puerto){
        this.puerto = puerto;
        try {
            servidor = new ServerSocket(puerto);
        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public SocketServidor(){
        //datos por defecto
    }
    
    public void run(){
        iniciar();
    }
    
    /**
     * Para iniciar el servidor
     * @param num El numero que le pasamos...
     */
    public void iniciar(){
        conectado = true;
        recibir();
    }
    
    public void pausar(){
        conectado = false;
    }
    
    public void reanudar(){
        conectado = true;
    }
    
    public void cerrar(){
        //ESTO YA SE HARIA DESDE EL STOP DEL HILO
    }
    
    public void recibir(){
        try {
            
            while(true){
                
                DatagramSocket  mySocket = new DatagramSocket(PUERTO_POR_DEFECTO);
                
                byte[] buffer = new byte[TAMAÑO_MAXIMO_PAQUETE];
                DatagramPacket datagram = new DatagramPacket(buffer, TAMAÑO_MAXIMO_PAQUETE);
                mySocket.receive(datagram);
                
                String mensajeRecibido = new String(buffer);
                //System.out.println(mensajeRecibido);
                if(conectado && dispositivoAceptado(datagram.getAddress().toString(), mensajeRecibido)){
                    procesarRecepcion(mensajeRecibido);
                }
                mySocket.close();
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void procesarRecepcion(String mensaje){
        
        //TODO: Poner estas variables en condiciones y comprobar que el paquete acabe en Accion.FINAL
        String tipoAccion = mensaje.substring(0, 3);
        
        String codigoAccion;
        
        codigoAccion = mensaje.trim();
        codigoAccion = codigoAccion.substring(0, codigoAccion.length() - Accion.LONGITUD);
        codigoAccion = codigoAccion.substring(3, codigoAccion.length());
        
        System.out.println(mensaje);
        
        /*//VERSION NO COMPATIBLE CON JAVA6
        switch (tipoAccion){
            case Accion.COMANDO:
                new Comando(codigoAccion);
                break;
                
            case Accion.RATON:
                new Raton(Integer.parseInt(codigoAccion));
                break;
                
            case Accion.TECLADO:
                new Tecla(codigoAccion);
                break;
                
            case Accion.TEXTO:
                new Texto(codigoAccion);
                break;
        }*/
        if (tipoAccion.equals(Accion.COMANDO)) new Comando(codigoAccion);
        if (tipoAccion.equals(Accion.RATON)) new Raton(Integer.parseInt(codigoAccion));
        if (tipoAccion.equals(Accion.TECLADO)) new Tecla(codigoAccion);
        if (tipoAccion.equals(Accion.TEXTO)) new Texto(codigoAccion);
        
    }
    
       public void teclear(int t1, int t2){
        try {
            Robot robot = new Robot();
           
            robot.keyPress(t1); 
            robot.keyPress(t2); 
            robot.keyRelease(t2);
            robot.keyRelease(t1);
            
        } catch (AWTException ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    public void enviar(String mensaje){
        
    }
    
    private boolean dispositivoAceptado(String ip, String mensaje){
        return true;
        /*mensaje = mensaje.trim();
        String dispositivo = mensaje.substring(3, (mensaje.length()-6));
        
        boolean resultado = false;
        
        for(int i=0; i< dispositivos.size(); i++){
            if(dispositivos.get(i).IP.equals(ip)){
                if(dispositivos.get(i).habilitado){
                    resultado = true;
                }else{
                    int n = JOptionPane.showConfirmDialog(
                    null,
                    "Aceptar dispositivo?",
                    dispositivo,
                    JOptionPane.YES_NO_OPTION);
                System.out.println("n"+n);
                if(n==0){
                    dispositivos.add(new Dispositivo(mensaje, ip, true));
                }else 
                    if(n==1){
                    dispositivos.add(new Dispositivo(mensaje, ip, false));
                    }  
                }    
            }
        }
        if(dispositivos.size() == 0){
            int n = JOptionPane.showConfirmDialog(
                    null,
                    "Aceptar dispositivo?",
                    dispositivo,
                    JOptionPane.YES_NO_OPTION);
                System.out.println("n"+n);
                
            if(n==0){
                dispositivos.add(new Dispositivo(mensaje, ip, true));
            }else if(n==1){
                dispositivos.add(new Dispositivo(mensaje, ip, false));
            }    
        }
        return resultado;*/
    }
    
}
