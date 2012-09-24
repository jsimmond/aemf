package cl.utfsm.aemf;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cl.utfsm.aemf.behaviorlistener.BehaviorListener;
import cl.utfsm.aemf.behaviormanager.BehaviorManager;
import cl.utfsm.aemf.eventstrategy.EventContext;
import cl.utfsm.aemf.util.Globals;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * I chose to use a IntentService because this is the best recommended alternative against Service class. 
 * This service based on IntentService don't need to handle multiple requests simultaneously. 
 * @author sebastian
 */
public class BehaviorService extends IntentService {

	  /** 
	   * A constructor is required, and must call the super IntentService(String)
	   * constructor with a name for the worker thread.
	   */
	  public BehaviorService() {
	      super("BehaviorListenerServiceThread");
	  }
	  
	  public BehaviorService(String name) {
	      super(name);
	  }
	  
	  @Override
	  public void onCreate() {

		  super.onCreate();
		  
		  // First thread, define a Thread that reads the logs
    	  Thread listenerThread = new Thread(new BehaviorListener());
    	  
    	  // The thread will executes this task until the service will be destroyed
    	  listenerThread.start();
    	  
	  }
	      
	  

	      
	  @Override
	  public void onDestroy() {
		  // TODO Eliminar este toast
		  //Toast.makeText(this, "Servicio destruido", 9000).show();
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