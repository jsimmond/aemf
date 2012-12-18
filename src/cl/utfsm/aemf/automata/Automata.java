package cl.utfsm.aemf.automata;

import java.util.ArrayList;
import java.util.Map.Entry;

import cl.utfsm.aemf.logger.AEMFLogger;


/**
 * 
 * @author Sebastián Vásquez Morales
 *
 */
public class Automata {

	private int id;
	private String fileName;
	private State currentState;
	private ArrayList<State> states = new ArrayList<State>();

	
	/**
	 * Process the event given and change the actual state if the event is accepted as a transition
	 * @param event
	 */
	public boolean processTransitionParameters(TransitionConfiguration parameters) {
		
		
		AEMFLogger.write("Current state of the Automata "+currentState.getId()+": " + currentState.getName());
		// For each transition, verify his configuration
		for(Transition t : currentState.getTransitions()){
			State nextState;
			if((nextState = t.getNextStateGivenConfiguration(parameters)) != null){
							
				AEMFLogger.write("Automata " + getId() + " ("+getFileName()+") change state from " +currentState.getId()+" to "+nextState.getId() );
				
				// Change the current state to next
				currentState = nextState;
				break;
			}
		}
		
		return false;
	}
	
	
	/*
	 * Public methods
	 */
	/**
	 * Set the initial state
	 * @return
	 */
	public void setInitialState(){
		for(State s : states){
			if(s.getStateType() == State.INITIAL_STATE) {
				currentState = s;
				return;
			}
		}
		currentState = states.get(0);
		return;
	}
	

	/**
	 * Verify is the automaton is in a final state
	 * @return
	 */
	public boolean isFinished() {
		return currentState.getStateType() == State.FINAL_STATE;
	}

	/**
	 * Add a new state to this automaton
	 * @param state
	 */
	public void addState(State state) {
		states.add(state);
	}
	
	/*
	 * Getters and setters
	 */
	public ArrayList<State> getStates() {
		return states;
	}
	
	// For debbuging
	public void printAutomaton()
	{
		for(State state : states){
			System.out.println("State " + state.getId() + "(type:"+state.getStateType()+"):");
			for(Transition transition : state.getTransitions()){
				System.out.println("Transition id: " + transition.getParameters().getTransitionId());
				System.out.println("-> Transition from "+transition.getFromState() + " to " + transition.getToState().getId());
				System.out.println("-> Parameters:");
				for(Entry<String, ArrayList<String>> entry : transition.getParameters().getHashMap().entrySet()){
					for(String val : entry.getValue()){
						System.out.println("--> " + entry.getKey() + " : " + val);
					}
				}
			}
		}
	}

	/*
	 * Getters and setters
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}
	
	public State getCurrentState(){
		return currentState;
	}
	
	
	
	
}
