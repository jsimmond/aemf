package act;

import cl.utfsm.aemf.BehaviorListener;
import cl.utfsm.aemf.BehaviorService;
import cl.utfsm.aemf.automaton.Automaton;
import cl.utfsm.aemf.automaton.Symbol;
import cl.utfsm.aemf.event.AutomatonEvent;
import cl.utfsm.aemf.event.AutomatonListener;
import cl.utfsm.aemf.manager.BehaviorManager;
import cl.utfsm.aemf.textevent.TextEvent;
import cl.utfsm.aemf.util.AEMFConfiguration;
import cl.utfsm.monitotingframework.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		if(BehaviorListener.isRunning()){
			Toast.makeText(this, "El servicio ya esta corriendo...", Toast.LENGTH_LONG).show();
			ToggleButton t = (ToggleButton) findViewById(R.id.toggleButtonActiveService);
			t.setChecked(true);
		}

	}

	
	/**
	 * Tareas comunes para iniciar AEMF
	 */
	private void initAEMF() {
		
		//1 Configurar parametros comunes
		AEMFConfiguration.APPLICATION_ID_TO_BE_MONITORED = "cl.vasquez.MainPackage";
		AEMFConfiguration.AEMF_SOURCE_FILES_DIRECTORY 
						= Environment.getExternalStorageDirectory().getAbsolutePath() + "/AEMF_files/";	//points to /mnt/sdcard/AEMF_files/
		
		//2 Iniciar servicio listener
		Intent intent = new Intent(this, BehaviorService.class);
		
		startService(intent);
		
		//3 Suscribirse a los eventos
		BehaviorManager bm = new BehaviorManager();
		bm.addAutomatonChangeStateEventListener(new AutomatonListener() {
			
			@Override
			public void handleAutomatonEvent(AutomatonEvent e) {
				
				// The automaton affected by a change
				Automaton a = e.getAutomaton();
				TextEvent t = e.getTextEvent();
				Symbol s = e.getSymbol();
				
				/**
				 * Do anything you want with your automatons!
				 */
				a.printAutomaton();
				t.printEvent();
				s.printSymbol();
				
			}
		});
	}

	public void onToggleClicked(View v){
		boolean on = ((ToggleButton) v).isChecked();
		
		if(on){
			// Iniciamos una instancia de AEMF
			initAEMF();
			Toast.makeText(this, "Servicio iniciado", Toast.LENGTH_LONG).show();

		}
		else
		{
			// Parar listener
			BehaviorListener.stop();
			Toast.makeText(this, "Servicio detenido", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onDestroy() {
		finish();
		super.onDestroy();
	}

}
