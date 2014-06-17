package tk.edurapp.pcremoto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.RecognizerIntent;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

public class InicioActivity extends Activity implements SensorEventListener, View.OnClickListener{

	
	//Elemento graficos
	private TabHost tabs;
	private ImageView imgTouchpad;
	
	//Pestaña ratón
	private Button btRaton1;
	private Button btRatonCentral;
	private Button btRaton2;
	private CheckBox cbAcelerometro;
	
	private SensorManager sm;
	
	//Pestaña teclado
	private Button btTextoSimple;
	private Button btTextoPass;
	private Button btPulsaHabla;
	private Button btTeclasFavoritas;
	private Button btTeclaSimple;
	private Button btTeclaCompuesta;
	private ListView lvTeclas;
	
	private static final int CODIGO_RECONOZIMIENTO_VOZ = 1234;
	private static final int maxLength = 990;  
	
	private ScaleGestureDetector scaleGestureDetector;
	private GestosMultiTactiles scaleListener;
	private String ip;
	
	//POSICIONES ANTERIORES DEL RATON
	private int anteriorPosicionRatonX = -1;
	private int anteriorPosicionRatonY = -1;
	private long milisegundoToqueAnterior = 0;
	private static final int TIEMPO_ENTRE_CLICK = 300;
	
	//ELEMENTOS DE LA PESTAÑA PLANTILLA
	private int[] idTeclas;
	private Button [] teclasPlantilla;
	private Spinner spPlantillas;
	private int[] iconosTeclas = {0xe71a, 0xe773, 0xe719, 0xe70f, 0xe6cd, 0xe710, 0xe70d, 0xe704, 0xe70e, 0,0,0,0,0,0};
	private String[] codigoTeclas = {""+Accion.VK_DOWN, ""+Accion.VK_SPACE, ""+Accion.VK_UP, ""+Accion.VK_0, ""+Accion.VK_5, ""+Accion.VK_END, ""+Accion.VK_LEFT, ""+Accion.VK_ESCAPE, ""+Accion.VK_RIGHT, "", "", "", "", "", "", };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_principal);
		
		ip = leerConfiguracionString("ip");
		
		new Cliente(ip, new Accion().primeraConexion()).start();
		iniciarElementos();
		configurarPestañas();
		pulsacionBotones();
		teclasPlantilla();
		
	}
	
	private void iniciarElementos(){
		tabs = (TabHost)findViewById(android.R.id.tabhost);
		imgTouchpad = (ImageView) findViewById(R.id.imgTouchpadTab);
		
		btRaton1 = (Button) findViewById(R.id.btRaton1);
		btRatonCentral = (Button) findViewById(R.id.btRatonCentral);
		btRaton2 = (Button) findViewById(R.id.btRaton2);
		cbAcelerometro = (CheckBox) findViewById(R.id.cbAcelerometro);
		
		btTextoSimple = (Button) findViewById(R.id.btTextoSimple);
		btTextoPass = (Button) findViewById(R.id.btTextoPass);
		btPulsaHabla = (Button) findViewById(R.id.btPulsaHabla);
		btTeclasFavoritas = (Button) findViewById(R.id.btTeclasFavoritas);
		btTeclaSimple = (Button) findViewById(R.id.btTeclaSimple);
		btTeclaCompuesta = (Button) findViewById(R.id.btTeclaCompuesta);
		
		spPlantillas = (Spinner) findViewById(R.id.spPlantillas);
		
		scaleListener = new GestosMultiTactiles(ip);
		scaleGestureDetector = new ScaleGestureDetector(this, scaleListener);
	}
	
	private void configurarPestañas(){
			
    	tabs.setup();
    	
    	TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
    	spec.setContent(R.id.tab1);
    	spec.setIndicator("", getResources().getDrawable(R.drawable.ic_action_keyboard));
    	tabs.addTab(spec);
    	
    	spec=tabs.newTabSpec("mitab2");
    	spec.setContent(R.id.tab2);
    	spec.setIndicator("", getResources().getDrawable(R.drawable.ic_action_mouse));
    	tabs.addTab(spec);
    	
    	spec=tabs.newTabSpec("mitab3");
    	spec.setContent(R.id.tab3);
    	spec.setIndicator("", getResources().getDrawable(R.drawable.ic_action_slideshow));
    	tabs.addTab(spec);
    	
    	spec=tabs.newTabSpec("mitab4");
    	spec.setContent(R.id.tab4);
    	spec.setIndicator("", getResources().getDrawable(R.drawable.ic_action_dial_pad));
    	tabs.addTab(spec);
    	
    	tabs.setCurrentTab(1);
    	
	}
	
	private void pulsacionBotones(){
		
		//FUNCION TECLADO
		btTextoSimple.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				pedirTexto("", false);
			}
		});
		
		btTextoPass.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				pedirTexto("", true);
			}
		});
		
		btPulsaHabla.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				startVoiceRecognitionActivity();
			}
		});
		
		//FUNCION TECLAS
		btTeclasFavoritas.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				
			}
		});
		
		btTeclaSimple.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				mostrarTeclas(false);
			}
		});
		
		btTeclaCompuesta.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				mostrarTeclas(true);
			}
		});
		
		//FUNCIONES RATÓN
		btRaton1.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
						Accion.RATON_CLICK_IZQUIERDO)).start();
			}
		});
		
		btRaton2.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
						Accion.RATON_CLICK_DERECHO)).start();
			}
		});
		
		btRatonCentral.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
						Accion.RATON_CLICK_CENTRAL)).start();
			}
		});
		
		if(leerConfiguracionBoolean("acelerometro")) cbAcelerometro.setChecked(true);
		
		cbAcelerometro.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if(arg1){
					guardarConfiguracion("acelerometro","true");
				}else{
					guardarConfiguracion("acelerometro","false");
				}
			}
		});
		
		btTeclasFavoritas.setVisibility(View.GONE);
		
	}
	
	private void teclasPlantilla(){
		
		Button btTecla0 = (Button) findViewById(R.id.btTecla0);
		Button btTecla1 = (Button) findViewById(R.id.btTecla1);
		Button btTecla2 = (Button) findViewById(R.id.btTecla2);
		Button btTecla3 = (Button) findViewById(R.id.btTecla3);
		Button btTecla4 = (Button) findViewById(R.id.btTecla4);
		Button btTecla5 = (Button) findViewById(R.id.btTecla5);
		Button btTecla6 = (Button) findViewById(R.id.btTecla6);
		Button btTecla7 = (Button) findViewById(R.id.btTecla7);
		Button btTecla8 = (Button) findViewById(R.id.btTecla8);
		Button btTecla9 = (Button) findViewById(R.id.btTecla9);
		Button btTecla10 = (Button) findViewById(R.id.btTecla10);
		Button btTecla11 = (Button) findViewById(R.id.btTecla11);
		Button btTecla12 = (Button) findViewById(R.id.btTecla12);
		Button btTecla13 = (Button) findViewById(R.id.btTecla13);
		Button btTecla14 = (Button) findViewById(R.id.btTecla14);
		
		teclasPlantilla = new Button[] {btTecla0, btTecla1, btTecla2, btTecla3, btTecla4, btTecla5, btTecla6, 
				btTecla7, btTecla8, btTecla9, btTecla10, btTecla11, btTecla12, btTecla13, btTecla14};
		
		idTeclas = new int[teclasPlantilla.length];
		
		ArrayAdapter <String> adaptadorSpinner=new ArrayAdapter<String> 
			(this,android.R.layout.simple_list_item_1 , Plantilla.LISTA_NOMBRES);
		
		spPlantillas.setAdapter(adaptadorSpinner);
		spPlantillas.setSelection(0);
		
		spPlantillas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long indice) { 
		    	
		    	Plantilla p = new Plantilla();
		    	iconosTeclas = p.listaIconos(posicion);
		    	codigoTeclas = p.listaTeclas(posicion);
		    	
		    	for(int j = 0; j < iconosTeclas.length; j++){
		    		idTeclas[j] = teclasPlantilla[j].getId();
					int cont = j;
					establecerTextoPlantilla(teclasPlantilla[j], cont);
		    	}
		    }
		    
			public void onNothingSelected(AdapterView<?> arg0) {}
		});  
		
		
		
		for(int i = 0; i < teclasPlantilla.length; i++){
			idTeclas[i] = teclasPlantilla[i].getId();
			teclasPlantilla[i].setOnClickListener(this);
			int posicion = i;
			establecerTextoPlantilla(teclasPlantilla[i], posicion);
		}
	}

	
	public void establecerTextoPlantilla(Button boton, int posicion){
		
		char a = (char) iconosTeclas[posicion];
		
		if (iconosTeclas[posicion]==0){
			boton.setEnabled(false);
		}else{
			boton.setEnabled(true);
		}
		
		boton.setText(""+a);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "icomoon.ttf");
		boton.setTypeface(font);
		
	}

	
	private void pedirTexto(String textoInicial, boolean pass){
		
		final EditText input = new EditText(this);
		input.setText(textoInicial);
		input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
		input.setSingleLine(false);
		input.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
		
		
		if(pass){
			input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}
		
		String titulo = this.getResources().getString(R.string.enviar_texto);
		String enviar = this.getResources().getString(R.string.enviar);
		String enviarEnter = this.getResources().getString(R.string.enviar_con_enter);
		String cancelar = this.getResources().getString(R.string.cancelar);
		
		AlertDialog alertDialog = new AlertDialog.Builder(this)
        .setTitle(titulo)
        .setView(input)
        .setPositiveButton(enviar, new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            	 String mensaje = input.getText().toString();
            	 new Cliente(ip, Accion.TEXTO + mensaje + Accion.FINAL).start();
            }
        }).setNegativeButton(cancelar, new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        }).setNeutralButton(enviarEnter, new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	String mensaje = input.getText().toString();
           	 	new Cliente(ip, Accion.TEXTO + mensaje + Accion.FINAL).start();
           	 	try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
           	 	new Cliente(ip, Accion.TECLADO + Accion.VK_ENTER + Accion.FINAL).start();
            }
        }).create();
		
        alertDialog.show();
	}
	
	
	//RESULTADO DEL RECONOCIMIENTO DE VOZ
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if (requestCode == CODIGO_RECONOZIMIENTO_VOZ && resultCode == RESULT_OK) {
             ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
             if(matches.size() > 0) {
            	 String textoReconocido=matches.get(0);
             	 pedirTexto(textoReconocido, false);
             }
         }
	}
	
	public void startVoiceRecognitionActivity() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
                .getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        
        try{
        	startActivityForResult(intent, 1234);
        }
        catch(Exception e){
        	Toast.makeText(this.getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
	}
	
	public void mostrarTeclas(final boolean atajo){
		
		String tecla = this.getResources().getString(R.string.TECLA);
		String[] datosLista = new String[Accion.LISTA_NOMBRES_TECLAS.length];
		
		for(int i=0; i<datosLista.length; i++){
			datosLista[i] = Accion.LISTA_NOMBRES_TECLAS[i].replace("%", tecla);
		}
		
		lvTeclas = new ListView(this);
		ArrayAdapter<String> adaptador;
		
		if(atajo){
			adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, datosLista);
			lvTeclas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		}else{
			adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, datosLista);
			lvTeclas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}

		lvTeclas.setAdapter(adaptador);
		
		
		AlertDialog alertDialog = new AlertDialog.Builder(this)
			.setTitle(tecla)
			.setView(lvTeclas)
			.setPositiveButton(this.getResources().getString(R.string.enviar), new AlertDialog.OnClickListener(){
				public void onClick(DialogInterface arg0, int arg1) {
					
					try{
						if(atajo){
							long[] seleccionados = lvTeclas.getCheckItemIds();
							String teclas="";
							if(seleccionados.length==0){
								Toast.makeText(getApplicationContext(), R.string.no_seleccionado, Toast.LENGTH_LONG).show();
							}else{
								for(int i =0; i < seleccionados.length; i++){
									teclas += Accion.LISTA_CODIGOS_TECLAS[(int)seleccionados[i]] + ";";
								}
								if(seleccionados.length>3){
									Toast.makeText(getApplicationContext(), R.string.maximo_teclas, Toast.LENGTH_LONG).show();
								}else{
									new Cliente(ip, Accion.TECLADO + teclas + Accion.FINAL).start();
								}
							}
						}else{
							try{
								int posicion = (int) lvTeclas.getCheckedItemPosition();
								new Cliente(ip, Accion.TECLADO + Accion.LISTA_CODIGOS_TECLAS[posicion] + Accion.FINAL).start();
							}catch(Exception e){
								Toast.makeText(getApplicationContext(), R.string.no_seleccionado, Toast.LENGTH_LONG).show();
							}
						}
					}
					catch (Exception e){
						Log.e("InicioActivity", "Seleccion de tecla");
					}
				}
			})
			.create();
		alertDialog.show();
		
	}
	
	/**
	 * Leemos los valores del desplazamiento por la pantalla del usaurio,
	 * obteniendo los valores de la vista en la que se encuentra el
	 * touchpad en la pestaña del ratón.
	 */
	public boolean onTouchEvent(MotionEvent event) {
		scaleGestureDetector.onTouchEvent(event);
		
		if(!scaleListener.gesto){
			//Coordenadas de la ubicacion en pantalla pulsada
			int posicionX = (int) event.getX();
			int posicionY = (int) event.getY();
			
			
			/*aqui estaria bien llamar a una clase "GestionRaton" que se encargase
			  de leer las poiciones de "x" e "y" para generar el mensaje a enviar
			  al servidor, pudiendose usar tanto en el metodo del acelerometro como
			  en el ratón a pantalla completa */
	
			Date momentoActual=new Date();
			
			//en caso de que haga doble click
			if(event.getAction()==MotionEvent.ACTION_DOWN){
	
				long diferenciaTiempo=momentoActual.getTime()-milisegundoToqueAnterior;
	
				if(diferenciaTiempo < TIEMPO_ENTRE_CLICK){
					//Hacemos click
					new Cliente(ip, new Accion().codigoEnvio(Accion.RATON, Accion.RATON_CLICK_IZQUIERDO)).start();
				}
			}
			
			milisegundoToqueAnterior=momentoActual.getTime();
			int eventAction = event.getAction();

			
		    if(posicionX>anteriorPosicionRatonX){
		    	new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
						Accion.RATON_MOVIMIENTO_DERECHA)).start();
		    }else{
		    	if(posicionX!=anteriorPosicionRatonX){
			    	new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
							Accion.RATON_MOVIMIENTO_IZQUIERDA)).start();
		    	}
		    }
		    
		    if(posicionY>anteriorPosicionRatonY){
		    	new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
						Accion.RATON_MOVIMIENTO_ABAJO)).start();
		    }else{
		    	if(posicionY!=anteriorPosicionRatonY){
			    	new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
							Accion.RATON_MOVIMIENTO_ARRIBA)).start();
		    	}
		    }
	
		    anteriorPosicionRatonY=posicionY;
		    anteriorPosicionRatonX=posicionX;
		}
		return false;
	}

	//TODO: METODOS PARA EL CONTROL DEL ACELEROMETRO
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		int x = (int)(event.values[SensorManager.DATA_X]);
		int y = (int)(event.values[SensorManager.DATA_Y]);

		int LENTO=2;
		int RAPIDO=5;
		
		if (cbAcelerometro.isChecked()){
			if(x>LENTO) new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
					Accion.RATON_MOVIMIENTO_IZQUIERDA)).start();
			if(x<-LENTO) new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
					Accion.RATON_MOVIMIENTO_DERECHA)).start();
			if(y>LENTO) new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
					Accion.RATON_MOVIMIENTO_ABAJO)).start();
			if(y<-LENTO) new Cliente(ip, new Accion().codigoEnvio(Accion.RATON,
					Accion.RATON_MOVIMIENTO_ARRIBA)).start();
		}
		//TODO: Implementar movimiento mas rapido y relativo a las coordenadas
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
    protected void onStart()
    {
         super.onStart();
         sm = (SensorManager) getSystemService(SENSOR_SERVICE);
         List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
         if (sensors.size() > 0) //dispositivo android tiene acelerometro
         {
             sm.registerListener(this, sensors.get(0), 1000000);
         }
    }
    
	@Override
	protected void onPause() {
		super.onPause();
		sm.unregisterListener(this);
	}
    
    
	
	private String leerConfiguracionString(String clave){
		SharedPreferences settings = getSharedPreferences(Configuracion.FICHERO_CONFIGURACION, MODE_PRIVATE);
		return settings.getString(clave, "error");
	}
	
	private boolean leerConfiguracionBoolean(String clave){
		SharedPreferences settings = getSharedPreferences(Configuracion.FICHERO_CONFIGURACION, MODE_PRIVATE);
		return settings.getBoolean(clave, false);
	}
	
	private void guardarConfiguracion(String clave, String valor){
		SharedPreferences settings = getSharedPreferences(Configuracion.FICHERO_CONFIGURACION, MODE_PRIVATE); 
		SharedPreferences.Editor edit = settings.edit();
		

		
		try{
			boolean valorBool = false;
			if(valor.equals("true")) valorBool = true;
			edit.putBoolean(clave, valorBool);	
		}catch (Exception e){}
		
		edit.commit();
	}

	//Control de las pulsaciones sobre las plantillas
	@Override
	public void onClick(View v) {
		
		int id = v.getId();
		
		for (int i = 0;  i < idTeclas.length; i++){
			if(id==idTeclas[i]){
				new Cliente(ip, Accion.TECLADO + codigoTeclas[i] + Accion.FINAL ).start();
			}
		}
		
	}
	
	
}