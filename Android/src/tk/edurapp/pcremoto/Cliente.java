package tk.edurapp.pcremoto;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;

public class Cliente extends Thread{
	
	private int puerto;
	private String ip;
	private String mensaje;
	private static final int PUERTO_POR_DEFECTO = 35550;
	
	public Cliente (String ip, int puerto, String mensaje){
		this.ip=ip;
		this.puerto=PUERTO_POR_DEFECTO;
		this.mensaje=mensaje;
	}
	
	public Cliente(String ip, String mensaje){
		this.ip=ip;
		puerto = 35550;
		this.mensaje=mensaje;
	}
	
	public void run(){
		
		try{
	        InetAddress hostDestino = InetAddress.getByName(ip);
	        
	        // Creamos el datagrama
	        DatagramSocket  socket = new DatagramSocket();
	        byte[ ] buffer = mensaje.getBytes();
	        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, hostDestino, puerto);
	        
	        socket.send(datagram);
	        socket.close( );
		}
		catch(Exception e){
			Log.e("Cliente", "Error");
		}
		
	}
}
