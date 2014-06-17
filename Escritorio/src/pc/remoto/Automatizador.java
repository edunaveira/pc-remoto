/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pc.remoto;

import java.util.ArrayList;

/**
 *
 * @author enaveira
 */
public class Automatizador {
    private ArrayList<Accion> acciones;
    
    public Automatizador (Accion a){
        acciones = new ArrayList();
        acciones.add(a);
    }
    
    public Automatizador (){
        acciones = new ArrayList();
    }
    
    public void añadirAccion(Accion a){
        acciones.add(a);
    }
    
    public String[] listaAcciones(){
        String[] lista=new String[acciones.size()];
        for(int i=0; i <acciones.size(); i++){
            lista[i] = acciones.get(i).descripcion;
        }
        return lista;
    }
    
    public void eliminarAccion(int posicion){
        acciones.remove(posicion);
    }
    
}
