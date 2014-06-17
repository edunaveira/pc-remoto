/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pc.remoto;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enaveira
 */
public class Tecla extends Accion{
    
    private String listaDeTeclas;
    
    public Tecla (String listaDeTeclas){
        this.listaDeTeclas = listaDeTeclas;
        pulsar(dividirListaDeTeclas());
    }
    
    public Tecla(){
        
    }
    
    public void pulsar(int[] codigosTeclas){
        
        try {
            
            Robot r = new Robot();
            r.keyPress(codigosTeclas[0]);
            
            //TODO: TESTEAR ESTE METODO
            if(codigosTeclas.length > 1){
                for(int i=1; i < codigosTeclas.length; i++){
                    r.keyPress(codigosTeclas[i]);                   
                } 
                for(int i=codigosTeclas.length-1; i >= 0; i--){
                    r.keyRelease(codigosTeclas[i]);
                }
            }
            
            r.keyRelease(codigosTeclas[0]);
            
        } catch (Exception e) {
            System.err.println("Tecla - Pulsar");
        }
    }
    
    private int[] dividirListaDeTeclas(){
        
        
        String[] lista = listaDeTeclas.split(";");
        int[] listaCodigos = new int[lista.length];
        
        try{
            for(int i = 0; i < lista.length; i++){ 
                int tecla=Integer.parseInt(lista[i]);

                if (tecla == 0) tecla = KeyEvent.VK_ENTER;
                if (tecla == 1) tecla = KeyEvent.VK_BACK_SPACE;
                if (tecla == 2) tecla = KeyEvent.VK_TAB;

                listaCodigos[i] = tecla;
            }
        }catch(Exception e){
            System.err.println("Tecla - dividir");
        }
        return listaCodigos;
    }
    
}
