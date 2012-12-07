package cl.utfsm.aemf.textevent;

import cl.utfsm.aemf.util.ServicesListened;

/**
 * This Class represents any event listened by LogCat. LogcatEvent can parse any 
 * log message written in the circular buffer
 * @author sebastian
 */

public class TextEventParser {

	// This object TODO explicar estoooo
	/**
	 * @uml.property  name="event"
	 * @uml.associationEnd  
	 */
	TextEvent event;
	
	// Constructor
	public TextEventParser(String line) {
		// Create the event object
		parseEvent(line);
	}

	/**
	 * Get the description text and service of a given event string. It returns null if the TAG was not recognized.
	 * @param event_log
	 */
	public void parseEvent(String event_log) {
		int match_service = -1;
		
		// First, reads if there are an known Android service 
		// TODO poner lineas obvias a ignorar? como ------begining of...
		for(int i = 0; i < ServicesListened.tagArrayListened.length; i++) {
			
			// Search if there is an known service/tag
			match_service = event_log.indexOf(ServicesListened.tagArrayListened[i] + ": ");
			
			// If it was found
			if(match_service != -1) {
				//returns 09-23 20:51:48.439   162   192 I ActivityManager: [Displayed cl.vasquez.MainPackage/.CuponListViewActivity: +449ms]
				event = new TextEvent();
				if(getTime_Date_Level_Application(event_log.split(ServicesListened.tagArrayListened[i]+": ")[0])){
					event.setTag(ServicesListened.tagArrayListened[i]);
					event.setText(event_log.split(ServicesListened.tagArrayListened[i]+": ")[1]);
					return;
				}
				else {
					event = null;
					return;
				}
				
				
			}
			else {
				event = null;
			}
		}
		
	}
	
	/**
	 * Parse and get the time, date, level and the application involved in the message.
	 * @param data
	 * @return true if the process was successful
	 */
	private boolean getTime_Date_Level_Application(String data) {
			String date_time_level_pid[] = data.split(" ");
	
			event.setDate(date_time_level_pid[0]); 					 		 // get date
			event.setTime(date_time_level_pid[1]); 							 // get time
			event.setPID(date_time_level_pid[4]);							 // get PID
			event.setLevel(date_time_level_pid[date_time_level_pid.length - 1]); // get level
			return true;
	}
	
	/**
	 * Verify if the event read is defined
	 * @return true if it does
	 */
	public boolean eventIsDefined() { 
		return event.equals(null);
	}
	
	/**
	 * Getters and setters
	 * @uml.property  name="event"
	 */
	public TextEvent getEvent(){
		return event;
	}

	
	

}
