package cl.utfsm.aemf.behaviormanager;


public class BehaviorTransition {
	
	private int fromState;
	private BehaviorState toState;
	
	private String transitionText;
	
	public BehaviorState getToState() {
		return toState;
	}

	public void setToState(BehaviorState toState) {
		this.toState = toState;
	}

	
	
	private boolean passed = false;
	
	
	public BehaviorState goToNextState() {
		return toState;
	}
	
	public void setPassed()
	{
		passed = true;
	}

	public int getFromState() {
		return fromState;
	}

	public void setFromState(int fromState) {
		this.fromState = fromState;
	}

	public String getTransitionText() {
		return transitionText;
	}

	public void setTransitionText(String transitionText) {
		this.transitionText = transitionText;
	}
	
}
