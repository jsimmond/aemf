package cl.utfsm.aemf.automaton;


public class Transition {
	
	/**
	 * @uml.property  name="fromState"
	 */
	private int fromState;
	/**
	 * @uml.property  name="toState"
	 * @uml.associationEnd  inverse="transitions:cl.utfsm.aemf.automaton.State"
	 */
	private State toState;
	/**
	 * @uml.property  name="symbolText"
	 */
	private String symbolText;
	
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
	public String getSymbolText() {
		return symbolText;
	}

	/**
	 * @param  transitionText
	 * @uml.property  name="symbolText"
	 */
	public void setSymbolText(String transitionText) {
		this.symbolText = transitionText;
	}
	
}
