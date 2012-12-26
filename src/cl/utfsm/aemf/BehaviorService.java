package cl.utfsm.aemf;

import cl.utfsm.aemf.file.AlphabetFile;
import cl.utfsm.aemf.file.BehaviorFile;
import cl.utfsm.aemf.file.TokenFile;
import cl.utfsm.aemf.logger.AEMFLogger;
import cl.utfsm.aemf.manager.BehaviorManager;
import cl.utfsm.aemf.util.AEMFConfiguration;
import cl.utfsm.aemf.util.Util;

import android.app.IntentService;
import android.content.Intent;

/**
 * Use a IntentService because this is the best recommended
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
		
		// try to get the PID of monitored application
		Util.getProcessId(AEMFConfiguration.APPLICATION_ID_TO_BE_MONITORED);
		
		// opens a stream to AEMF log file
		AEMFLogger.openNewStream(AEMFConfiguration.AEMF_SOURCE_FILES_DIRECTORY + AEMFConfiguration.LOG_FILE_NAME);
		AEMFLogger.writeInfo("Starting new session of AEMF");
		AEMFLogger.writeInfo("=> Monitoring application " + AEMFConfiguration.APPLICATION_ID_TO_BE_MONITORED);
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
			BehaviorManager.automatonList = bf.getAutomataList();
			BehaviorManager.symbolList 	  = af.getSymbolList();
			BehaviorManager.tokenList 	  = tf.getTokenList();
			
			AEMFLogger.write("There are " + BehaviorManager.automatonList.size() + " automata/on to be monitored");
			
		} catch (Exception e) {
			AEMFLogger.write("There was a problem reading the files...");
			e.printStackTrace();
			System.exit(0);
		}

		// First thread, define a Thread that reads the logs
		Thread listenerThread = new Thread(new BehaviorListener());

		// The thread will executes this task
		listenerThread.start();
		AEMFLogger.write("Listener service started");
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
		return;
	}
}