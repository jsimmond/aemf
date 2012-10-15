package cl.utfsm.aemf.automaton;

import java.util.ArrayList;


/**
 * 
 * @author Sebastián Vásquez Morales
 *
 */
public class Automaton {

	ArrayList<State> states = new ArrayList<State>();
	private State currentState;
	
	
	/**
	 * Process the event given and change the actual state if the event is accepted as a transition
	 * @param event
	 */
	public boolean processTransition(String symbolId, TransitionParameters parameters) {
		
		/**
		 * TODO Implementar 
		 */
		System.out.println("currentstateTransitions: " + currentState.getId());
		for(Transition t : currentState.getTransitions()){
			System.out.println(" -> " + t.getSymbolText());
		}
		System.out.println("processTransition: " + symbolId);
		//System.out.println("processTransition: " + parameters.getTransitionId() );
		return false;
	}
	
	
	/*
	 * Public methods
	 */
	/**
	 * Set the initial state
	 * @return
	 */
	public State setInitialState(){
		for(State s : states){
			if(s.getStateType() == State.INITIAL_STATE)
				return s;
		}
		return states.get(0);
	}
	

	/**
	 * Verify is the automaton is in a final state
	 * @return
	 */
	public boolean isFinished() {
		if ( currentState.getStateType() == State.FINAL_STATE) {
			return true;
		}
		return false;
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
				System.out.println("-> Transition from "+transition.getFromState() + " to " + transition.getToState().getId() + " - " + transition.getSymbolText());
			}
		}
	}
	
	
	
	
}
