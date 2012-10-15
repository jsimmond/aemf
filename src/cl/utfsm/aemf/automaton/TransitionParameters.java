package cl.utfsm.aemf.automaton;

import java.util.ArrayList;
import java.util.HashMap;

public class TransitionParameters {

	private String transitionId;
	private HashMap<String, ArrayList<String>> hp;
	
	/**
	 * Constructor
	 */
	public TransitionParameters() {
		hp = new HashMap<String, ArrayList<String>>();
	}

	/**
	 * Set the transition id
	 * @param transitionId
	 */
	public void setTransitionId(String transitionId) {
		this.transitionId = transitionId;
	}
	
	// Get transition id
	public String getTransitionId() {
		return transitionId;
	}
	
	/**
	 * Returns the hashmap
	 * @return
	 */
	public HashMap<String, ArrayList<String>> getHashMap(){
		return hp;
	}
	
	/**
	 * Add a new entry to hash map
	 * @param key
	 * @param value
	 */
	public void addParameter(String key, String value){
		if(hp.containsKey(key))
			hp.get(key).add(value);
		else {
			ArrayList<String> s = new ArrayList<String>();
			s.add(value);
			hp.put(key, s);
		}
	}
	
	
}
