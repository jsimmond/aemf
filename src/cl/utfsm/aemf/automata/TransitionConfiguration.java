package cl.utfsm.aemf.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class TransitionConfiguration {

	private String transitionId;
	private HashMap<String, ArrayList<String>> hp;
	private int PID;
	
	/**
	 * Constructor
	 */
	public TransitionConfiguration() {
		hp = new HashMap<String, ArrayList<String>>();
		PID = -1;
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

	public void setPID(int pid) {
		this.PID = pid;
	}
	
	public int getPID(){
		return PID;
	}
	
	/**
	 * Gets a parameter given its index
	 * @param index
	 * @return String[]
	 */
	public String getFormatParametersString(){
		String res = "";
		
		for(Entry<String, ArrayList<String>> entry : this.hp.entrySet()){
			
			if(entry.getKey().equals("activity") || entry.getKey().equals("application")) {
				for(String val : entry.getValue()){
					res += entry.getKey() + ":" +  val;
				}		
				res += ", ";	
			}
			
		}
		
		return res.substring(0, res.length()-2);
	}
	
	
}
