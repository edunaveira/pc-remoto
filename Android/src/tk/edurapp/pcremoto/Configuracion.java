package tk.edurapp.pcremoto;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

public class Configuracion extends PreferenceActivity{
	
	public static final String FICHERO_CONFIGURACION = "tk.edurapp.pcremoto_preferences";
	
	private Preference qr;
	private Preference servidor;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.configuracion);
        
        qr = findPreference("qr");
        servidor = findPreference("ip");
        
        qr.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
            	lanzarEscanerQR();
                return false;
            }
        });
        
    }
	
	private void lanzarEscanerQR(){
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
		try{
			IntentResult resultadoEscaneado = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
			
			//TODO: comprobar que esto funcione correctamente
			if (resultadoEscaneado != null && (!resultadoEscaneado.equals(""))) {
				String contenidoEscaneado = resultadoEscaneado.getContents();
				guardarConfiguracion("ip", contenidoEscaneado);
				Toast.makeText(getApplicationContext(), contenidoEscaneado.replace(";", "\n"), Toast.LENGTH_LONG).show();
				Log.e("Codigo QR", contenidoEscaneado);
			}
		} 
		catch(Exception e){
			Log.e("Codigo QR", "ERROR resultado QR");
			//captura el error cuando regresa de a la actividad sin escanear el codigo
		}
	}
	
	private void guardarConfiguracion(String clave, String valor){
		SharedPreferences settings = getSharedPreferences(FICHERO_CONFIGURACION, MODE_PRIVATE);
		SharedPreferences.Editor edit = settings.edit();
		
		edit.putString(clave, valor);
		edit.commit();
	}

}
