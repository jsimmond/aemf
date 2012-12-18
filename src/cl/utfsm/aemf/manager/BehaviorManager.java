package cl.utfsm.aemf.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import cl.utfsm.aemf.automata.Automata;
import cl.utfsm.aemf.automata.Symbol;
import cl.utfsm.aemf.automata.TransitionConfiguration;
import cl.utfsm.aemf.event.AutomataEvent;
import cl.utfsm.aemf.event.AutomataListener;
import cl.utfsm.aemf.logger.AEMFLogger;
import cl.utfsm.aemf.textevent.TextEvent;
import cl.utfsm.aemf.token.BadTokenException;
import cl.utfsm.aemf.token.Token;
import cl.utfsm.aemf.util.AEMFConfiguration;
import cl.utfsm.aemf.util.Util;

/**
 * @author Sebastián Vásquez Morales
 */
public class BehaviorManager {


	// Listeners
	private static ArrayList<AutomataListener> _listeners = new ArrayList<AutomataListener>();
	

	public static ArrayList<Automata> automatonList = new ArrayList<Automata>();	// All the automatons to manage
	public static ArrayList<Symbol> symbolList 		 = new ArrayList<Symbol>();		// Alphabet to compare
	public static ArrayList<Token> tokenList 		 = new ArrayList<Token>();		// Tokens of alphabet to compare
	
	/**
	 * Compare the event consumed and the actual state of each automaton  
	 * @param event
	 * @throws BadTokenException 
	 */
	public static boolean processEvent(TextEvent event) throws BadTokenException {
		
		Symbol sym = getSymbolToUse(event.getText());
		
		// parameters will be null if the event does not accepted
		TransitionConfiguration parameters = Inspector.getTransitionFromEvent(event.getText(), sym);
								parameters.setTransitionId(sym.getId());
		
		// verify if the symbol requires a PID
		if(sym.isPIDRequired()){
			
			// does exists a process_id key on params received?
			if(parameters.getPID() > 0){
				// if it exists, then verify if whether its value (the PID) belongs
				// to the monitored application
				int monitoredApplicationPID = Util.getProcessId(AEMFConfiguration.APPLICATION_ID_TO_BE_MONITORED);
				int foreignApplicationPID = parameters.getPID();
				
				if(monitoredApplicationPID != foreignApplicationPID)
					return false;
				
			}
		}
		
		if(parameters != null){
			// Verify if the transition is possible
			for(Automata automaton : automatonList)
			{
				AEMFLogger.write("Processing event " + sym.getId() + "("+ parameters.getFormatParametersString() +") on Automata " + automaton.getId() + " ("+automaton.getFileName()+")");
				automaton.processTransitionParameters(parameters);
				
				// Fire event!
				fireChangeStateEvent(automaton, event, sym, parameters);
				
				if(automaton.isFinished()){
					AEMFLogger.write("Automata " + automaton.getId() + " Finished on state " + automaton.getCurrentState().getId());
					return true;
				}
					
			}
		}
		
		return false;
		
	}
	
	/**
	 * Get the appropriate symbol given by eventText parameter
	 * @param eventText
	 * @return a symbol object
	 */
	public static Symbol getSymbolToUse(String eventText){
		
		// The event string as an ArrayList
		LinkedList<String> eventArrayText = new LinkedList<String>(Arrays.asList(eventText.split(" ")));
		
		// get all the symbols available
		for(Symbol sym : BehaviorManager.symbolList){

			// get text from each symbol
			ArrayList<String> symArrayText = getStringArrayWithoutTokens(sym.getText().split(" "));
			int symArrayTextSize = symArrayText.size();
			for(String s : symArrayText){

				// if the string exists in the first element of the array
				if(!eventArrayText.contains(s)){
					
					//The symbol does not useful
					break;
				}
				else {
					// pop the string element from the array
					eventArrayText.remove(s);
					symArrayTextSize--;
				}
			}
			
			if(symArrayTextSize == 0){
				return sym;
			}
		}
		return null;
	}

	/**
	 * Removes all the string who has tokens and returns the text
	 * @param split
	 * @return an arrayList with token ("application", "activity", etc) String elements
	 */
	private static ArrayList<String> getStringArrayWithoutTokens(String[] split) {
		
		ArrayList<String> res = new ArrayList<String>();
		for(String s : split){
			if(!s.matches(".*\\{\\%[a-zA-Z_]+\\%\\}.*")){
				res.add(s);
			}
		}
		return res;
	}
	
	
	/**
	 * Get the token with tokenId id 
	 * @param tokenId
	 * @return a Token with than id
	 */
	public static Token getTokenById(String tokenId){
		for(Token t : tokenList){
			if(t.getTokenId().equals(tokenId))
				return t;
		}
		return null;
	}
	
	
	/*
	 * Getters and setters
	 */
	public static ArrayList<Automata> getAutomatonList() {
		return automatonList;
	}

	public static void setAutomatonList(ArrayList<Automata> automatonList) {
		BehaviorManager.automatonList = automatonList;
	}

	public static void addAutomaton(Automata a) {
		BehaviorManager.automatonList.add(a);
	}
	
	public static void addSymbol(Symbol s) {
		BehaviorManager.symbolList.add(s);
	}

	public static ArrayList<Symbol> getSymbolList() {
		return symbolList;
	}

	/**
	 * Add a new event listener
	 * @param listener
	 */
	public synchronized void addAutomatonChangeStateEventListener(AutomataListener listener)  {
		 _listeners.add(listener);
	}
	

	/**
	 * Call this method whenever you want to notify
	 * the event listeners of the particular event
	 * @param Automata, TextEvent, Symbol
	 */
	private synchronized static void fireChangeStateEvent(Automata a, TextEvent t, Symbol s, TransitionConfiguration p) {
		AutomataEvent event = new AutomataEvent(a, t, s, p);
		Iterator<AutomataListener> i = _listeners.iterator();
		while(i.hasNext())  {
	      ((AutomataListener) i.next()).handleAutomatonEvent(event);
	    }
	}


}
