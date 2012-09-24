package cl.utfsm.aemf.behaviorlistener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.util.Log;
import cl.utfsm.aemf.behaviormanager.BehaviorManager;
import cl.utfsm.aemf.event.Event;
import cl.utfsm.aemf.eventstrategy.EventContext;
import cl.utfsm.aemf.util.Globals;

/**
 * This class implements the Runnable interface. It reads de circular log for events
 * @author Sebastián Vásquez Morales
 *
 */
public class BehaviorListener implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
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

				EventContext logcat_event = new EventContext(line);
				
				// If the event is defined, then compare with the automatons
				if(logcat_event.eventIsDefined()) {
					Event event = logcat_event.getEvent();
					
					// Process the event with BehaviorManager
					BehaviorManager.processEvent(event);
				}
			}
		} catch (Exception e) {
			// Error occurred while reading the buffer log
			Log.e(Globals.APPLICATION_TAG, Globals.ERROR_AT_READ_LOG);
		}

	}

}
