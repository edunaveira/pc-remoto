package tk.edurapp.pcremoto;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;

public class AyudaActivity extends Activity {
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_ayuda);
		cargarWebAyuda();
	}
	
	/**
	 * Carga el contenido de documento de ayuda.
	 */
	private void cargarWebAyuda(){
		
		webView = (WebView) findViewById(R.id.wbAyuda);
		
		//TODO: Cargar ARCHIVO LOCAL
		webView.loadUrl("http://www.remotepc.tk");
		//habilitamos JavaScript
		webView.getSettings().setJavaScriptEnabled(true);
	}

}
