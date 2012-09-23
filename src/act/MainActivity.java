package act;

import java.util.ArrayList;
import java.util.Timer;

import cl.utfsm.aemf.behaviorListener.BehaviorListenerService;
import cl.utfsm.aemf.behaviorfile.BehaviorFile;
import cl.utfsm.aemf.behaviormanager.BehaviorAutomaton;
import cl.utfsm.aemf.behaviormanager.BehaviorState;
import cl.utfsm.monitotingframework.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	private BehaviorListenerService ListenerService;
	private Timer timer = new Timer();

	// Lista de eventos
	private ArrayList<BehaviorState> states;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		
		BehaviorFile bf;
		
		try {
			bf = new BehaviorFile();
			for( BehaviorAutomaton a : bf.getAutomatonList())
			{
				System.out.println("AUTOMATA");
				a.printAutomaton();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		// Iniciar servicio listener
	//	Intent intent = new Intent(this, BehaviorListenerService.class);
	//	startService(intent);
		// TODO Descomentar esto?
		//Toast.makeText(this, "Servicio iniciado", 10000).show();

		// Iniciar una lista local de eventos random
	//	states = new ArrayList<BehaviorState>();

		// 'NN-NN NN:NN:NN.NNN PID PID2 {W|I|E|D} {ServiceName}: {RestOfText}'
	}
	
	
	public class StartupIntentReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Intent serviceIntent = new Intent();
			serviceIntent
					.setAction("cl.utfsm.aemf.behaviorListener.BehaviorListenerService");
			context.startService(serviceIntent);
		}

	}

	@Override
	public void onDestroy() {
		finish();
		super.onDestroy();
	}

}
