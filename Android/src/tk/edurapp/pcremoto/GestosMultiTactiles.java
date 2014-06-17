package tk.edurapp.pcremoto;

import java.util.ArrayList;

import android.util.Log;
import android.view.ScaleGestureDetector;


public class GestosMultiTactiles extends
ScaleGestureDetector.SimpleOnScaleGestureListener {
	
	public boolean gesto;
	private ArrayList<Float> escalas;
	private String ip;
	
	public GestosMultiTactiles(String ip){
		this.ip=ip;
	}
	
	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		
		escalas = new ArrayList<Float>();
		return super.onScaleBegin(detector);
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {
		
		float sumaEscalas=0;
		for(int i = 0; i < escalas.size(); i++){
			sumaEscalas+= escalas.get(i);
		}
		float escalaZoom = sumaEscalas / escalas.size();
		
		if(escalaZoom > 1){
			//AMPLIAR
			Log.e("log", "ampliar");
			Cliente cli =new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
					Accion.RATON_ZOOM_MAS));
			cli.start();
		}else{
			//REDUCIR
			Log.e("log", "reducir");
			
			Cliente cli =new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
					Accion.RATON_ZOOM_MENOS));
			cli.start();
		}
		gesto = false;
		super.onScaleEnd(detector);
		
	}

	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		gesto=true;
		float escalaZoom = detector.getScaleFactor();    
		escalas.add(escalaZoom);
		//TODO: Guardar las ultimas escalas y actuar segun las mismas.
		
		
		return true;
	}
}