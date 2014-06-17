/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pc.remoto;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enaveira
 */
public class Comando {
    
    public Comando(String comando){
        ejecutarComando(comando);
    }
    
    public String ejecutarComando(String comando){
        
        String resultado="";
        //TODO: SACAR EL TEXTO DEVUELTO...
        //http://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
        
        try {
            
            Runtime rt = Runtime.getRuntime();
            rt.exec(comando);
            
        } catch (IOException ex) {
            Logger.getLogger(Comando.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
