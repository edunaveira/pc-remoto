/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pc.remoto;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;



/**
 *
 * @author enaveira
 */
public class GeneradorQR {
    
    private String texto;
    
    public GeneradorQR(String texto){
        this.texto=texto;
        generar(texto);
    }
    
    public GeneradorQR(){
        
    }
    
    //TODO: Metodo que nos devuelva la imagen del codigo QR 
    
    public ImageIcon generar(String codigo){
        ImageIcon imagen;
        try {
            byte[] imagenBytes;
            ByteArrayOutputStream out = QRCode.from(codigo).to(ImageType.PNG).stream();
            imagenBytes = out.toByteArray();
            InputStream is = new ByteArrayInputStream(imagenBytes);
            BufferedImage myPicture = ImageIO.read(is);
            myPicture.getScaledInstance(100, 100, 0);
            imagen = new ImageIcon(myPicture.getScaledInstance(350, 350, 0));
            return imagen;
        } catch (IOException ex) {
            System.err.println("Error generando QR");
            return null;
        }
    }
}
