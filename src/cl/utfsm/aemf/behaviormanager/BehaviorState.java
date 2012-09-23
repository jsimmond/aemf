package cl.utfsm.aemf.behaviormanager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class define an state as expected (or not) under a certain behavior. This
 * class can be the parent of other classes.
 * @author sebastian
 *
 */
public class BehaviorState {

	
	private int id;						// Some ID to identify this state
	private String label;				// 
	private String name;				//
	private int stateType;				//initial, intermediate or final state
	private ArrayList<BehaviorTransition> transitions = new ArrayList<BehaviorTransition>();
	
	// Some static constants
	public static int INITIAL_STATE 	 = 0;
	public static int INTERMEDIATE_STATE = 1;
	public static int FINAL_STATE 		 = 2;
	
	
	/**
	 * Constructor
	 */
	public BehaviorState()
	{
		this.stateType = -1;
	}
	
	
	
	
	
	/**
	 * GETTERS AND SETTERS
	 */
	public int getStateType() {
		return stateType;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}






	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}






	public void setLabel(String label) {
		this.label = label;
		
	}
	public String getLabel() {
		return this.label;
		
	}






	public void setStateType(int stateType) {
		this.stateType = stateType;
		
	}






	public void addTransition(BehaviorTransition transition) {
		// TODO Auto-generated method stub
		this.transitions.add(transition);
	}
	public ArrayList<BehaviorTransition> getTransitions(){
		return transitions;
	}
	 

} 