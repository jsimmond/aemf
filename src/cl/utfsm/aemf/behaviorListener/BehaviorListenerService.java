package cl.utfsm.aemf.behaviorListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cl.utfsm.aemf.util.ApplicationListened;
import cl.utfsm.aemf.util.Globals;
import cl.utfsm.aemf.util.ServicesListened;
import android.app.IntentService;
import android.content.Context;
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
	      final Context cc = this;
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
			    	Process process = Runtime.getRuntime().exec("logcat -v threadtime ActivityManager:* *:S");
			    	BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			    	
			    	// This while read each new line on the buffer
			    	while ((line = br.readLine()) != null) {
			    		
			    		//Log.i("AEMF", line);
			    		ActivityManagerParser(line);
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
	      
	  public void ActivityManagerParser(String event_log) {
			String date = null;
			String time = null;
			String PID = null;
			String level = null;
			String service = null;
			String application = null;
			String text = null;
			
			int match_service = -1;
			int match_application = -1;
			String data[] = null;
			String text_application[] = null;
			
			// First, reads if there are an known Android service 
			for(int i = 0; i < ServicesListened.servicesArrayListened.length; i++) {
				
				match_service = event_log.indexOf(ServicesListened.servicesArrayListened[i] + ": ");
				
				if(match_service != -1) {
					service = ServicesListened.servicesArrayListened[i]; // get service
					data = event_log.split(service + ": ");
					// Second, reads if there are an known Application
					for(int j = 0; j < ApplicationListened.applicationArrayListened.length; j++) {
						match_application = data[1].indexOf(ApplicationListened.applicationArrayListened[j]);
						if(match_application != -1) {
							application = ApplicationListened.applicationArrayListened[j]; // get application
					
							String date_time_level[] = data[0].split(" ");
					
							date = date_time_level[0]; 							 // get date
							time = date_time_level[1]; 							 // get time
							level = date_time_level[date_time_level.length - 1]; // get level
							
							text_application = data[1].split(application);
							break;
						}
					}
					break;
				}
			}
			
			// Third, verify if the two conditions above has done
			if(match_service != -1 && match_application != -1)
			{
				text = text_application[0] + " - " + text_application[1];
				
				Log.i("Fecha", date);
				Log.i("Hora", time);
				Log.i("Nivel", level);
				Log.i("Servicio", service);
				Log.i("Aplicacion", application);
				Log.i("Texto", text);
				
			} 
			else
			{
				return;
			}
		
			
			
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