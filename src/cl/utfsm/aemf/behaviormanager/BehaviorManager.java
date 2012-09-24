package cl.utfsm.aemf.behaviormanager;

import java.io.IOException;
import java.util.ArrayList;

import cl.utfsm.aemf.behaviorfile.BehaviorFile;
import cl.utfsm.aemf.event.Event;

/**
 * 
 * @author Sebastián Vásquez Morales
 *
 */
public class BehaviorManager {

	// All the automatons to manage
	private static ArrayList<BehaviorAutomaton> automatonList;
	
	/**
	 * Read the path given by directory, and load each automaton
	 * @param directory
	 */
	public static void loadAutomatonsFromDirectory(String directory) {
		BehaviorFile behaviorFile;
		try {
			behaviorFile = new BehaviorFile();
			setAutomatonList(behaviorFile.getAutomatonList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Compare the event consumed and the actual state of each automaton  
	 * @param event
	 */
	public static void processEvent(Event event) {
		for(BehaviorAutomaton automaton : automatonList){
			automaton.consumeEvent(event);
		}
	}
	
	
	/*
	 * Getters and setters
	 */
	public static ArrayList<BehaviorAutomaton> getAutomatonList() {
		return automatonList;
	}

	public static void setAutomatonList(ArrayList<BehaviorAutomaton> automatonList) {
		BehaviorManager.automatonList = automatonList;
	}
}
