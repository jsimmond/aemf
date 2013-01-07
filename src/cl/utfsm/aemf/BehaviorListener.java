package cl.utfsm.aemf;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.util.Log;
import cl.utfsm.aemf.logger.AEMFLogger;
import cl.utfsm.aemf.manager.BehaviorManager;
import cl.utfsm.aemf.textevent.TextEventParser;
import cl.utfsm.aemf.util.AEMFConfiguration;

/**
 * This class implements the Runnable interface. It reads de circular log for events
 * @author Sebastián Vásquez Morales
 *
 */
public class BehaviorListener implements Runnable {

	// Necessary synchronization token
	private static boolean syncToken = false;
	
	public void run() {
		
		try {
			BehaviorListener.syncToken = true;
			
			String line = "";			
			
			// First, we need to clear the buffer logs to begin reads
			Runtime.getRuntime().exec("logcat -c");

			// Second, we start to read at real time logs
			Process process = Runtime.getRuntime().exec(
					"logcat -v threadtime ActivityManager:* *:S");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			// This while read each new line on the buffer
			while ((line = br.readLine()) != null) {

				try{
					if(!syncToken){
						AEMFLogger.writeInfo("Listener services stopped unexpectedly.");
						this.finalize();
					}
					
					TextEventParser logcat_event = new TextEventParser(line);
					if(logcat_event.getEvent() != null){
						if(BehaviorManager.processEvent(logcat_event.getEvent()))
						{
							syncToken = false;
							this.finalize();
						}
					}
							
				}
				catch (Throwable e) {
					e.printStackTrace();
				}
				

			}
		} catch (Exception e) {
			// Error occurred while reading the buffer log
			AEMFLogger.writeInfo("Error desconocido");
			Log.e(AEMFConfiguration.APPLICATION_TAG, AEMFConfiguration.ERROR_AT_READ_LOG+": " + e.getMessage());
		}

	}

	public static void stop() {
		BehaviorListener.syncToken = false;
		AEMFLogger.writeInfo("Freeing resources...");
	}

	public static boolean isRunning() {
		return syncToken;
	}

}
