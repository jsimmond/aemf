package cl.utfsm.aemf.automaton;

import java.util.ArrayList;

/**
 * This class define an state as expected (or not) under a certain behavior. This
 * class can be the parent of other classes.
 * @author Sebastián Vásquez Morales
 *
 */
public class State {

	
	/**
	 * @uml.property  name="id"
	 */
	private int id;						// Some ID to identify this state
	/**
	 * @uml.property  name="label"
	 */
	private String label;				// 
	/**
	 * @uml.property  name="name"
	 */
	private String name;				//
	/**
	 * @uml.property  name="stateType"
	 */
	private int stateType;				//initial, intermediate or final state
	/**
	 * @uml.property  name="transitions"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="toState:cl.utfsm.aemf.automaton.Transition"
	 */
	private ArrayList<Transition> transitions = new ArrayList<Transition>();
	
	// Some static constants
	public static int INITIAL_STATE 	 = 0;
	public static int INTERMEDIATE_STATE = 1;
	public static int FINAL_STATE 		 = 2;
	
	
	/**
	 * Constructor
	 */
	public State()
	{
		this.stateType = -1;
	}
	
	
	public void addTransition(Transition transition) {
		// TODO Auto-generated method stub
		this.transitions.add(transition);
	}
	
	public ArrayList<Transition> getTransitions(){
		return transitions;
	}

	/**
	 * GETTERS AND SETTERS
	 * @uml.property  name="stateType"
	 */
	public int getStateType() {
		return stateType;
	}

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param  id
	 * @uml.property  name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param  name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param  label
	 * @uml.property  name="label"
	 */
	public void setLabel(String label) {
		this.label = label;
		
	}
	/**
	 * @return
	 * @uml.property  name="label"
	 */
	public String getLabel() {
		return this.label;
		
	}

	/**
	 * @param  stateType
	 * @uml.property  name="stateType"
	 */
	public void setStateType(int stateType) {
		this.stateType = stateType;
		
	}
	
} 