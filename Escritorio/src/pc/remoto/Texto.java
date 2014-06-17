/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pc.remoto;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 *
 * @author enaveira
 */
public class Texto extends Accion{
    
    
    public Texto(String mensaje){
        escribir(mensaje);
    }
    
    private void escribir(String mensaje){
 
        StringSelection stringSelection = new StringSelection(mensaje);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                stringSelection, null);
        
        new Tecla().pulsar(new int[]{KeyEvent.VK_CONTROL, KeyEvent.VK_V});
 
    }
    
}
