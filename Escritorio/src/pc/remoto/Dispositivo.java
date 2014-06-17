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
public class Dispositivo {
    
    public String id;
    public String nombre;
    public String descripcion;
    public String IP;
    public boolean habilitado;
    
    public Dispositivo(String nombre, String IP, boolean habilitado){
        this.nombre = nombre;
        this.IP = IP;
        this.habilitado = habilitado;
    }

    public void habilitar(){
        habilitado = true;
    }

}
