/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pc.remoto;

/**
 *
 * @author enaveira
 */
public class Accion {
    
    public int codigo;
    public String descripcion;
    public String tipo;
    
    //CONSTANTES PARA LEER EL TIPO DE DATO
    public static final int LONGITUD = 3;
    public static final String TECLADO = "TEC";
    public static final String RATON = "RAT";
    public static final String TEXTO = "TXT";
    public static final String COMANDO = "COM";
    
    //PARA COMPROBAR LOS MENSAJES Y EL DISPOSITIVO RECEPTOR
    public static final String CONEXION = "CON";
    public static final String FINAL = "FIN";

}
