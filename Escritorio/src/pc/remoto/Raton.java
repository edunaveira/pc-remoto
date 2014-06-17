/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pc.remoto;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enaveira
 */
public class Raton extends Accion{
    
    private int codigo;
    
    
    //CONSTANTES RATÓN
    public static final int RATON_CLICK_CENTRAL		= 10000;
    public static final int RATON_CLICK_DERECHO		= 10001;
    public static final int RATON_CLICK_IZQUIERDO	= 10002;
    public static final int RATON_MOVIMIENTO_ARRIBA	= 10003;
    public static final int RATON_MOVIMIENTO_IZQUIERDA	= 10004;
    public static final int RATON_MOVIMIENTO_DERECHA	= 10005;
    public static final int RATON_MOVIMIENTO_ABAJO	= 10006;
    public static final int RATON_SCROLL_SUBIR		= 10007;
    public static final int RATON_SCROLL_BAJAR		= 10008;
    public static final int RATON_ZOOM_MAS              = 10009;
    public static final int RATON_ZOOM_MENOS            = 10010;
    
    public static final int SENSIBILIDAD_BAJA = 2;
    public static final int SENSIBILIDAD_MEDIA = 6;
    public static final int SENSIBILIDAD_ALTA = 20;
    
        
    public Raton(int codigo){
        this.codigo = codigo;
        leer();
    }
    
    public Raton(int x, int y, int tecla, boolean soltar){
       //Constructor para cuando se realiza una atomatización 
    }

    private void leer(){
        
        if(codigo == RATON_CLICK_CENTRAL || codigo == RATON_CLICK_DERECHO 
                || codigo == RATON_CLICK_IZQUIERDO){
           click(); 
        }
        
        if (codigo == RATON_MOVIMIENTO_ARRIBA || codigo == RATON_MOVIMIENTO_IZQUIERDA
                || codigo == RATON_MOVIMIENTO_DERECHA || codigo == RATON_MOVIMIENTO_ABAJO){
            mover();
        }
        
        if (codigo == RATON_ZOOM_MAS || codigo == RATON_ZOOM_MENOS ){
            zoom();
        }
        
        if( codigo == RATON_SCROLL_SUBIR || codigo == RATON_SCROLL_BAJAR){
            scroll();
        }
        
    }

    private void mover(){
        
        try {
            //PARA OBTENER LAS COORDENADAS ACTUALES DEL RATON
             int x=0, y=0;
            PointerInfo a = MouseInfo.getPointerInfo();
            Point punto=null;
               
            try{
                punto = a.getLocation();

               //POSICION X E Y DEL RATON
               x = (int) punto.getX();
               y = (int) punto.getY();

            }catch(Exception e){}
            
            Robot r = new Robot();
            switch (codigo){
                case RATON_MOVIMIENTO_ARRIBA:
                    r.mouseMove(x, y - SENSIBILIDAD_MEDIA);
                    break;
                case RATON_MOVIMIENTO_IZQUIERDA:
                    r.mouseMove(x - SENSIBILIDAD_MEDIA, y);
                    break;
                case RATON_MOVIMIENTO_DERECHA:
                    r.mouseMove(x + SENSIBILIDAD_MEDIA, y);
                    break;
                case RATON_MOVIMIENTO_ABAJO:
                    r.mouseMove(x, y + SENSIBILIDAD_MEDIA);
                    break;
            }
        } catch (Exception e) {
             System.err.print("Raton - mover");
        }
    }

    private void click(){
        
        try {
            Robot r = new Robot();
            int codigoPulsado=0;
            
            switch(codigo){
                case RATON_CLICK_DERECHO:
                    codigoPulsado=InputEvent.BUTTON3_MASK;
                    break;
                case RATON_CLICK_IZQUIERDO:
                    codigoPulsado=InputEvent.BUTTON1_MASK;
                    break;
                case RATON_CLICK_CENTRAL:
                    codigoPulsado=InputEvent.BUTTON2_MASK;
                    break;
            }
            r.mousePress(codigoPulsado);
            r.mouseRelease(codigoPulsado);
            
        } catch (Exception e) {
            System.err.print("Raton - click");
        }
    }
    
    private void zoom(){
       
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_CONTROL);
            
            if (codigo == RATON_ZOOM_MAS){
                r.keyPress(KeyEvent.VK_PLUS);
                r.keyRelease(KeyEvent.VK_PLUS);
            }
            else
            {
                r.keyPress(KeyEvent.VK_MINUS);
                r.keyRelease(KeyEvent.VK_MINUS);
            }
            
            r.keyRelease(KeyEvent.VK_CONTROL);
            
        } catch (Exception e) {
             System.err.print("Raton - Zoom");
        }
        
    }
    
    private void scroll(){
        //TODO: probar este metodo...
        try {
            Robot r = new Robot();
            if(codigo == RATON_SCROLL_SUBIR){
                r.mouseWheel(100);
            }else{
                r.mouseWheel(-100);
            }
            
        } catch (Exception e) {
            System.err.print("Raton - scroll");
        }
        
    }
}
