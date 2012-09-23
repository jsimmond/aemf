package cl.utfsm.aemf.eventstrategy;

import cl.utfsm.aemf.event.Event;
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
		// Create the event strategy object
		eventStrategy = new EventStrategy();
		eventStrategy.setEventLog(line);
		parse(line);
	}

	public String parse(String event_log) {
		int match_service = -1;
		int match_application = -1;
		String data[] = null;
		String text_application[] = null;
		
		// First, reads if there are an known Android service 
		for(int i = 0; i < ServicesListened.servicesArrayListened.length; i++) {
			
			// Search if there is an known service
			match_service = event_log.indexOf(ServicesListened.servicesArrayListened[i] + ": ");
			
			// If it was found
			if(match_service != -1) {
				return ServicesListened.servicesArrayListened[i]; 
			}
			else {
				return null;
			}
		}
		
		// Third, verify if the two conditions above has done
		if(match_service != -1 && match_application != -1)
		{
			//text_application[0] + " - " + text_application[1];
			
		} 
		else
		{
			//return;
		}
		return event_log;
	}
	
	public void generateStrategy() {
		
		//event.setService(ServicesListened.servicesArrayListened[i]); 

	}

	
	public void getEvent() {
		// TODO Auto-generated method stub
		
	}

	
	

}
