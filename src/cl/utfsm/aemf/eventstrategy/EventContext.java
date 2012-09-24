package cl.utfsm.aemf.eventstrategy;

import cl.utfsm.aemf.event.Event;
import cl.utfsm.aemf.util.ApplicationListened;
import cl.utfsm.aemf.util.ServicesListened;

/**
 * This Class represents any event listened by LogCat. LogcatEvent can parse any 
 * log message written in the circular buffer
 * @author sebastian
 */

public class EventContext {

	// This object TODO explicar estoooo
	EventStrategy eventStrategy;
	Event event;
	
	// Constructor
	public EventContext(String line) {
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
		for(int i = 0; i < ServicesListened.servicesArrayListened.length; i++) {
			
			// Search if there is an known service/tag
			match_service = event_log.indexOf(ServicesListened.servicesArrayListened[i] + ": ");
			
			// If it was found
			if(match_service != -1) {
				//returns 09-23 20:51:48.439   162   192 I ActivityManager: [Displayed cl.vasquez.MainPackage/.CuponListViewActivity: +449ms]
				getTime_Date_Level_Application(event_log.split(ServicesListened.servicesArrayListened[i]+": ")[0]);
				
				event.setService(ServicesListened.servicesArrayListened[i]);
				event.setText(event_log.split(ServicesListened.servicesArrayListened[i]+": ")[1]); 
				
			}
			else {
				event = null;
			}
		}
		
	}
	
	/**
	 * Parse an get the time, date, level and the application involved in the message
	 * @param data
	 */
	private void getTime_Date_Level_Application(String data) {
		
		// Second, reads if there are an known Application
		// This is carried out by a Strategy pattern
		for(int j = 0; j < ApplicationListened.applicationArrayListened.length; j++) {
			
			if(data.indexOf(ApplicationListened.applicationArrayListened[j]) != -1) {
				
				event.setApplication(ApplicationListened.applicationArrayListened[j]); // get application
		
				String date_time_level[] = data.split(" ");
		
				event.setDate(date_time_level[0]); 					 		 // get date
				event.setTime(date_time_level[1]); 							 // get time
				event.setLevel(date_time_level[date_time_level.length - 1]); // get level
				
				break;
			}
			else {
				event = null;
			}
			
		}
		
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
	 */
	public Event getEvent(){
		return event;
	}

	
	

}
