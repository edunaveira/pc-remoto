package tk.edurapp.pcremoto;

public class Texto {
	
	private static final int LONGITUD_MAXIMA = 900;
	
	public Texto(String texto){
		leer(texto);
	}
	
	public Texto(String texto, boolean intro){
		
	}
	
	private void leer(String texto){
		if(texto.length()<50){
			//Lo guardamos en la base de datos de textos mas utilizados
			
			
			
		}else{
			if(texto.length() > LONGITUD_MAXIMA){
				
			}
		}
	}
	
	private void enviar(String texto){
		
	}

}
