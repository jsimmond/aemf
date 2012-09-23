package cl.utfsm.aemf.eventstrategy;

import android.util.Log;
import cl.utfsm.aemf.event.Event;
import cl.utfsm.aemf.util.ApplicationListened;
import cl.utfsm.aemf.util.ServicesListened;

/**
 * This Class represents any event listened by LogCat. LogcatEvent can parse any 
 * log message written in the circular buffer
 * @author sebastian
 */

public class EventStrategy {
	
	String event_log;
	
	//TODO explciarr!!
	Event event;
	
	
	/**
	 * Constructor
	 * @param evnt
	 */
	public EventStrategy() {
		event = new Event();
	}
	
	
	/**
	 * Set the event log message read from the buffer
	 * @param line
	 */
	public void setEventLog(String line) {
		// TODO Auto-generated method stub
		event_log = line;
	}
	
	
	/** 
	 * The main parser method. This method takes the event_log String, parse it and
	 * create the TODO object  
	 */
	public void parseService() {
			
		
		
	}


	private void parseApplication() {
		
		/*// TODO Auto-generated method stub
		data = event_log.split(event.getService() + ": ");
		// Second, reads if there are an known Application
		// This is carried out by a Strategy pattern
		for(int j = 0; j < ApplicationListened.applicationArrayListened.length; j++) {
			match_application = data[1].indexOf(ApplicationListened.applicationArrayListened[j]);
			if(match_application != -1) {
				event.setApplication(ApplicationListened.applicationArrayListened[j]); // get application
		
				String date_time_level[] = data[0].split(" ");
		
				event.setDate(date_time_level[0]); 					 		 // get date
				event.setTime(date_time_level[1]); 							 // get time
				event.setLevel(date_time_level[date_time_level.length - 1]); // get level
				
				text_application = data[1].split(event.getApplication());
				break;
			}
		}*/
	}

	
}
