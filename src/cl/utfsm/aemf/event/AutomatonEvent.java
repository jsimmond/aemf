package cl.utfsm.aemf.event;

import java.util.EventObject;

import cl.utfsm.aemf.automaton.Automaton;
import cl.utfsm.aemf.automaton.Symbol;
import cl.utfsm.aemf.textevent.TextEvent;

/**
 * This is the main event fired by the BehaviorManager class, it stores information
 * about the automaton affected by an event
 * @author Sebastián Vásquez Morales
 *
 */

@SuppressWarnings("serial")
public class AutomatonEvent extends EventObject{

	
	private Automaton automaton;
	private TextEvent textEvent;
	private Symbol symbol;

	/**
	 * Constructor
	 * @param source
	 */
	public AutomatonEvent(Automaton automaton, TextEvent textEvent, Symbol sym) {
		super("");
		this.automaton = automaton;
		this.textEvent = textEvent;
		this.symbol = sym;
	}

	/*
	 * Getters
	 */
	public Automaton getAutomaton() {
		return automaton;
	}
	
	public TextEvent getTextEvent() {
		return textEvent;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}



}
