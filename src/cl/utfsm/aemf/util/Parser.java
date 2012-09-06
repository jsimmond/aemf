package cl.utfsm.aemf.util;

import cl.utfsm.aemf.behaviorStates.BehaviorState;

public class Parser {
	
	public void readLogString(String str) {
		
		
		String level = "";
		String android_service = "";
		
		// Split the String on first ':' character (without quotes)
		String split_level_serviceproc[] = str.split(": ");
		
		// The first component of split[] is '{I|W|D|E|F}/{Service_Name}(	proc_id)' (without quotes)
		String split_level_service[] = split_level_serviceproc[0].split("/");
		
		// level = {I|W|D|E|F}
		level = split_level_service[0];
		
		// Una aplicacion puede nacer de PID 443 (android.process.acore) o ser hijo
		
		
		
		
		
	}

}
