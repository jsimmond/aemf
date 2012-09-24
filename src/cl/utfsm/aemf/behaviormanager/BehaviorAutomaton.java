package cl.utfsm.aemf.behaviormanager;

import java.util.ArrayList;

import cl.utfsm.aemf.event.Event;

/**
 * 
 * @author Sebastián Vásquez Morales
 *
 */
public class BehaviorAutomaton {

	
	private ArrayList<BehaviorState> states = new ArrayList<BehaviorState>();
	private BehaviorState currentState;
	
	/**
	 * Process the event given and change the actual state, if the event is accepted as a transition
	 * @param event
	 */
	public boolean consumeEvent(Event event) {
		// TODO completar esto
		return false;
	}
	
	
	/**
	 * Verify is the automaton is in a final state
	 * @return
	 */
	public boolean isFinished() {
		if ( currentState.getStateType() == BehaviorState.FINAL_STATE) {
			return true;
		}
		return false;
	}

	/**
	 * Add a new state to this automaton
	 * @param state
	 */
	public void addState(BehaviorState state) {
		states.add(state);
	}
	
	/*
	 * Getters and setters
	 */
	public ArrayList<BehaviorState> getStates() {
		return states;
	}
	
	// For debbuging
	public void printAutomaton()
	{
		for(BehaviorState state : states){
			System.out.println("State " + state.getId() + "("+state.getStateType()+"): {"+state.getId()+", "+state.getLabel()+", "+state.getName()+"}");
			for(BehaviorTransition transition : state.getTransitions()){
				System.out.println("-> Transition from "+transition.getFromState() + " to " + transition.getToState().getId());
			}
		}
	}
	
	
	
	
}
