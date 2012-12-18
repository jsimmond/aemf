package act;

import cl.utfsm.aemf.BehaviorListener;
import cl.utfsm.aemf.BehaviorService;
import cl.utfsm.aemf.automata.Automata;
import cl.utfsm.aemf.automata.Symbol;
import cl.utfsm.aemf.automata.TransitionConfiguration;
import cl.utfsm.aemf.event.AutomataEvent;
import cl.utfsm.aemf.event.AutomataListener;
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
		AEMFConfiguration.LOG_FILE_NAME = "log.txt";
		
		//2 Iniciar servicio listener
		Intent intent = new Intent(this, BehaviorService.class);
		startService(intent);
		
		//3 Suscribirse a los eventos
		BehaviorManager bm = new BehaviorManager();
		bm.addAutomatonChangeStateEventListener(new AutomataListener() {
			
			@Override
			public void handleAutomatonEvent(AutomataEvent e) {
				
				// The automaton affected by a change
				Automata a = e.getAutomaton();
				TextEvent t = e.getTextEvent();
				Symbol s = e.getSymbol();
				TransitionConfiguration p = e.getParameters();
				
				/**
				 * Do anything you want with your automatons!
				 */
				if(a.isFinished())
					System.out.println("El automata "+a.getId()+" (" + a.getFileName() + ") ha terminado llegando al estado " + a.getCurrentState().getId());
				else
					System.out.println("Automata " + a.getId() + " ha cambiado de estado a " + a.getCurrentState().getId() + " gracias a " + s.getId());
				
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
