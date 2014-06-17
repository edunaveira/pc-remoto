package tk.edurapp.pcremoto;

public class Plantilla {
	
	public int[] listaIconos;
	public String[] listaTeclas;
	
	
	//Lista de plantillas por defecto
	public static final int[] ICONOS_YOUTUBE = {0xe71a, 0xe773, 0xe719, 0xe70f, 0xe6cd, 0xe710, 0xe70d, 0xe704, 0xe70e, 0,0,0,0,0,0};
	public static final String[] TECLAS_YOUTUBE = {""+Accion.VK_DOWN, ""+Accion.VK_SPACE, ""+Accion.VK_UP, ""+Accion.VK_0, ""+Accion.VK_5, ""+Accion.VK_END, ""+Accion.VK_LEFT, ""+Accion.VK_ESCAPE, ""+Accion.VK_RIGHT, "", "", "", "", "", "", };
	
	public static final int[] ICONOS_PRESENTACION = {0xe726, 0xe613, 0xe722, 0xe67d, 0, 0, 0xe704, 0, 0xe722, 0,0,0,0,0,0};
	public static final String[] TECLAS_PRESENTACION = {""+Accion.VK_LEFT, ""+Accion.VK_F5, ""+Accion.VK_RIGHT, ""+Accion.VK_HOME, "", "", ""+Accion.VK_ESCAPE, "", ""+Accion.VK_SPACE, "","","","","",""};
	
	public static final int[] ICONOS_XBMC = {};
	public static final String[] TECLAS_XBMC = {};
	
	public static final int[] ICONOS_VLC = {};
	public static final String[] TECLAS_VLC = {};
	
	public static final int[] ICONOS_SPOTIFY = {};
	public static final String[] TECLAS_SPOTIFY = {};
	
	public static final String[] LISTA_NOMBRES = {"POWERPOINT", "YOUTUBE"};
	
	public Plantilla(){
		
	}
	
	public int[] listaIconos(int posicion){
		int[] lista = new int[15];
		
		if (posicion == 0) lista = ICONOS_PRESENTACION;
		if (posicion == 1) lista = ICONOS_YOUTUBE;
		if (posicion == 2) lista = ICONOS_XBMC;
		if (posicion == 3) lista = ICONOS_VLC;
		
		return lista;
	}
	
	public String[] listaTeclas(int posicion){
		String[] lista = new String[15];
	
		if (posicion == 0) lista = TECLAS_PRESENTACION;
		if (posicion == 1) lista = TECLAS_YOUTUBE;
		if (posicion == 2) lista = TECLAS_XBMC;
		if (posicion == 3) lista = TECLAS_VLC;
		
		return lista;
	}
	
	

}
