package cl.utfsm.aemf.behaviormanager;

import java.util.ArrayList;

public class BehaviorAutomaton {

	
	private ArrayList<BehaviorState> states = new ArrayList<BehaviorState>();
	private BehaviorState currentState;

	
	
	
	public void changeState() {
		
		
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

	public void addState(BehaviorState state) {
		states.add(state);
	}
	
	public ArrayList<BehaviorState> getStates()
	{
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
