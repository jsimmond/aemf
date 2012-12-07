package cl.utfsm.aemf.automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class Transition {
	
	private int fromState;
	private State toState;
	private TransitionConfiguration parameters;
	
	/**
	 * @return
	 * @uml.property  name="toState"
	 */
	public State getToState() {
		return toState;
	}

	/**
	 * @param  toState
	 * @uml.property  name="toState"
	 */
	public void setToState(State toState) {
		this.toState = toState;
	}

	
	/**
	 * @uml.property  name="passed"
	 */
	private boolean passed = false;
	
	
	public State goToNextState() {
		return toState;
	}
	
	public void setPassed()
	{
		passed = true;
	}
	
	public boolean getIfPassed()
	{
		return passed;
	}

	/**
	 * @return
	 * @uml.property  name="fromState"
	 */
	public int getFromState() {
		return fromState;
	}

	/**
	 * @param  fromState
	 * @uml.property  name="fromState"
	 */
	public void setFromState(int fromState) {
		this.fromState = fromState;
	}

	/**
	 * @return
	 * @uml.property  name="symbolText"
	 */
	public TransitionConfiguration getParameters() {
		return parameters;
	}

	
	/**
	 * Configure this transition, sets the transition ID and his parameters 
	 * @param transitionText
	 */
	public void config(String transitionText) {
		
		this.parameters = new TransitionConfiguration();
		
		// Process a string like start_intent()
		int start = transitionText.indexOf("(") + 1;
		parameters.setTransitionId(transitionText.substring(0, start - 1));		// set transition id
		
		String parametersString = transitionText.substring(start, transitionText.length() - 1);
			   parametersString = parametersString.replaceAll(" ", "");
		String parameters[] = parametersString.split(",");
		
		for(String s : parameters){
			String parameter[] = s.split(":");
			
			if(parameter[0].equalsIgnoreCase("app")){
				this.parameters.addParameter("application", parameter[1]);
			}
			else if(parameter[0].equalsIgnoreCase("act")){
				this.parameters.addParameter("activity", parameter[1]);
			}
		}
		
	}

	/**
	 * Returns the next state given a transition configuration. If the transition configuration is not valid
	 * for this Transition, then return a null object.
	 * @param config
	 * @return
	 */
	public State getNextStateGivenConfiguration(TransitionConfiguration config) {
		
		String transitionId = this.getParameters().getTransitionId();
		String transitionIdGuess = config.getTransitionId();
		
		// Is the same transitionId?
		if(transitionId.equalsIgnoreCase(transitionIdGuess)){
			// Is the hash map valid?
			if(isValidHashMap(config.getHashMap())){
				
				// Then, return the next state
				return this.toState;
			}
		}
		
		return null;
	}


	/**
	 * TODO NO se valida bien el hashmap
	 * @param hashMap
	 * @return
	 */
	private boolean isValidHashMap(HashMap<String, ArrayList<String>> hashMap) {
		HashMap<String, ArrayList<String>> thisHashMap = this.getParameters().getHashMap();
		
		System.out.println("This hashmap");
		for(Entry<String, ArrayList<String>> entry : thisHashMap.entrySet()){
			System.out.println(entry.getKey()+" = "+entry.getValue());
		}
		
		System.out.println("Foreign hashpmap");
		for(Entry<String, ArrayList<String>> entry : hashMap.entrySet()){
			System.out.println(entry.getKey()+" = "+entry.getValue());
		}
		
		// Analyze each entry of this hashmap
		for(Entry<String, ArrayList<String>> entry : thisHashMap.entrySet()){
			if(hashMap.get(entry.getKey()) == null){
				return false;
			}
			else
			{
				for(String s : hashMap.get(entry.getKey())){	// each foreign hashmap entry 
					for(String ss : thisHashMap.get(entry.getKey())){ //each hashmap host entry
						if(!s.equalsIgnoreCase(ss)){
							return false;
						}
					}
				}
			}
		}
		
		return true;
		
	}

		
		
	
	
}
