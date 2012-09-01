package cl.utfsm.aemf.behaviorListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cl.utfsm.aemf.util.Globals;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * I chose to use a IntentService because this is the best recommended alternative against Service class. 
 * This service based on IntentService don't need to handle multiple requests simultaneously. 
 * @author sebastian
 */
public class BehaviorListenerService extends IntentService {

	  /** 
	   * A constructor is required, and must call the super IntentService(String)
	   * constructor with a name for the worker thread.
	   */
	  public BehaviorListenerService() {
	      super("BehaviorListenerServiceThread");
	  }
	  
	  public BehaviorListenerService(String name) {
	      super(name);
	  }
	  
	  @Override
	  public void onCreate() {

		  super.onCreate();
	      
		  // Define a Thread that reads the logs
    	  Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					@SuppressWarnings("unused")
					String line = "";
					
					// First, we need to clear the buffer logs to begin reads
			    	Runtime.getRuntime().exec("logcat -c");	    	  
			    	
			    	// Second, we start to read at real time logs
			    	Process process = Runtime.getRuntime().exec("logcat ActivityManager:* *:S -v threadtime");
			    	BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			    	
			    	// This while read each new line on the buffer
			    	while ((line = br.readLine()) != null) {
			    		
			    		/**
			    		 * TODO Parsear los logs en estructuras de datos publicadas
			    		 */
			    		
			    	}
				}
				catch(Exception e)
				{
					// Error occurred while reading the buffer log
					Log.e(Globals.APPLICATION_TAG, Globals.ERROR_AT_READ_LOG);
				}
			}
    	  });
    	  
    	  // We used a thread because the while above is infinite, which
    	  // means that the parent activity stay waiting for the "end" of
    	  // this onCreate() method. The thread will executes this task
    	  // until the service will be destroyed
    	  thread.start();
          
	  }
	      
	      
	  @Override
	  public void onDestroy() {
		  // TODO Eliminar este toast
		  Toast.makeText(this, "Servicio destruido", 9000).show();
		  super.onDestroy();
	  }

	  /**
	   * The IntentService calls this method from the default worker thread with
	   * the intent that started the service. When this method returns, IntentService
	   * stops the service, as appropriate.
	   */
	  @Override
	  protected void onHandleIntent(Intent intent) {
	      // Normally we would do some work here, like download a file.
	      // For our sample, we just sleep for 5 seconds.
		  return;
	  }
	  
	  

	}