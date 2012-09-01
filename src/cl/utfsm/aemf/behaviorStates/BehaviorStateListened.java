package cl.utfsm.aemf.behaviorStates;

import java.util.List;

/**
 * This class define an state as expected (or not) under a certain behavior. This
 * class can be the parent of other classes.
 * @author sebastian
 *
 */
public class BehaviorStateListened extends BehaviorState {

	
	// id has an ID to identify this state
	// a level of the state (info, error, debug, etc)
	// and the application name that can cause the event
	
	private String tag;
	private String text;
} 