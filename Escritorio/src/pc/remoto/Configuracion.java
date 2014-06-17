/*
 * Copyright 2014 enaveira.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pc.remoto;

import java.util.ArrayList;

/**
 *
 * @author enaveira
 */
public class Configuracion {

    public boolean autoinicio;
    public int sensibilidad;
    public ArrayList<Automatizador> listaAcciones;
    
    private String RUTA_POR_DEFECTO="conf.dat";
   
    public Configuracion(){
       
    }
    
    public Configuracion(String ruta){
        RUTA_POR_DEFECTO = ruta;
    }
    
    public void leer(){
        
    }
    
    public void guardar(){
        
    }
}
