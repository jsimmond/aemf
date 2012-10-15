package cl.utfsm.aemf;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.util.Log;
import cl.utfsm.aemf.event.EventParser;
import cl.utfsm.aemf.manager.BehaviorManager;
import cl.utfsm.aemf.util.Globals;

/**
 * This class implements the Runnable interface. It reads de circular log for events
 * @author Sebastián Vásquez Morales
 *
 */
public class BehaviorListener implements Runnable {

	@Override
	public void run() {
		
		try {

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
					EventParser logcat_event = new EventParser(line);
					if(logcat_event.getEvent() != null)
						BehaviorManager.processEvent(logcat_event.getEvent());
				}catch(Exception e){}
			}
		} catch (Exception e) {
			// Error occurred while reading the buffer log
			Log.e(Globals.APPLICATION_TAG, Globals.ERROR_AT_READ_LOG+": " + e.getMessage());
		}

	}

}
