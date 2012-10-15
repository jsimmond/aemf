package cl.utfsm.aemf;

import cl.utfsm.aemf.automaton.Symbol;
import cl.utfsm.aemf.file.AlphabetFile;
import cl.utfsm.aemf.file.BehaviorFile;
import cl.utfsm.aemf.file.TokenFile;
import cl.utfsm.aemf.manager.BehaviorManager;
import cl.utfsm.aemf.util.Globals;
import cl.utfsm.aemf.util.Util;

import android.app.IntentService;
import android.content.Intent;

/**
 * I chose to use a IntentService because this is the best recommended
 * alternative against Service class. This service based on IntentService don't
 * need to handle multiple requests simultaneously.
 * 
 * @author sebastian
 */
public class BehaviorService extends IntentService {

	/**
	 * A constructor is required, and must call the super IntentService(String)
	 * constructor with a name for the worker thread.
	 */
	
	public BehaviorService() {
		super("BehaviorService");
	}

	public BehaviorService(String name) {
		super(name);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		Util.getProcessId(Globals.APPLICATION_ID_TO_BE_MONITORED);

	
		/*
		 * First of all, we need the read the configurations file,
		 * it can be done by the BehaviorFile class
		 */
		BehaviorFile bf;
		AlphabetFile af;
		TokenFile tf;
		try {
			/**
			 * Read automatons, alphabet and tokens files
			 */
			bf = new BehaviorFile("");
			af = new AlphabetFile("");
			tf = new TokenFile("");
			BehaviorManager.automatonList = bf.getAutomatonList();
			BehaviorManager.symbolList 	  = af.getSymbolList();
			BehaviorManager.tokenList 	  = tf.getTokenList();
		
			for(Symbol s : BehaviorManager.symbolList){
				s.printSymbol();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// First thread, define a Thread that reads the logs
		Thread listenerThread = new Thread(new BehaviorListener());

		// The thread will executes this task until the service will be
		// destroyed
		listenerThread.start();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	/**
	 * The IntentService calls this method from the default worker thread with
	 * the intent that started the service. When this method returns,
	 * IntentService stops the service, as appropriate.
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		// Normally we would do some work here, like download a file.
		// For our sample, we just sleep for 5 seconds.
		return;
	}

}