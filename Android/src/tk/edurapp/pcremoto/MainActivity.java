package tk.edurapp.pcremoto;

import java.util.Date;
import java.util.UUID;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(leerConfiguracion("id").equals("error")){
			guardarConfiguracion("id", generarID());
		}
	}

	//Acciones de los botones del menu principal
	
	public void clickInicio(View v){
		Intent intent = new Intent(this, InicioActivity.class);
		startActivity(intent);
	}
	
	public void clickConfiguracion(View v){
		Intent intent = new Intent(this, Configuracion.class);
		startActivity(intent);
	}
	
	public void clickAyuda(View v){
		Intent intent = new Intent(this, AyudaActivity.class);
		startActivity(intent);
	}
	
	public void clickSalir(View v){
		this.finish();
	}
	
	private String leerConfiguracion(String clave){
		SharedPreferences settings = getSharedPreferences(Configuracion.FICHERO_CONFIGURACION, MODE_PRIVATE);
		return settings.getString(clave, "error");
	}
	
	private void guardarConfiguracion(String clave, String valor){
		SharedPreferences settings = getSharedPreferences(Configuracion.FICHERO_CONFIGURACION, MODE_PRIVATE); 
		SharedPreferences.Editor edit = settings.edit();
		
		edit.putString(clave, valor);//insertamos una clave y un valor
		edit.commit();
	}
	
	private String generarID(){
		return UUID.randomUUID().toString();
	}

}
