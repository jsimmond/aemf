package cl.utfsm.aemf.event;

import java.util.EventObject;

import cl.utfsm.aemf.automata.Automata;
import cl.utfsm.aemf.automata.Symbol;
import cl.utfsm.aemf.automata.TransitionConfiguration;
import cl.utfsm.aemf.textevent.TextEvent;

/**
 * This is the main event fired by the BehaviorManager class, it stores information
 * about the automaton affected by an event
 * @author Sebastián Vásquez Morales
 *
 */

@SuppressWarnings("serial")
public class AutomataEvent extends EventObject{

	
	private Automata automaton;
	private TextEvent textEvent;
	private Symbol symbol;
	private TransitionConfiguration parameters;

	/**
	 * Constructor
	 * @param source
	 */
	public AutomataEvent(Automata automaton, TextEvent textEvent, Symbol sym, TransitionConfiguration params) {
		super("");
		this.automaton = automaton;
		this.textEvent = textEvent;
		this.symbol = sym;
		this.parameters = params;
	}

	/*
	 * Getters
	 */
	public Automata getAutomaton() {
		return automaton;
	}
	
	public TextEvent getTextEvent() {
		return textEvent;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public TransitionConfiguration getParameters(){
		return this.parameters;
	}



}
