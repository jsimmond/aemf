package cl.utfsm.aemf.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import cl.utfsm.aemf.automaton.Automaton;
import cl.utfsm.aemf.automaton.Symbol;
import cl.utfsm.aemf.automaton.TransitionParameters;
import cl.utfsm.aemf.event.Event;
import cl.utfsm.aemf.token.BadTokenException;
import cl.utfsm.aemf.token.Token;

/**
 * @author Sebastián Vásquez Morales
 */
public class BehaviorManager {


	public static ArrayList<Automaton> automatonList = new ArrayList<Automaton>();	// All the automatons to manage
	public static ArrayList<Symbol> symbolList 		 = new ArrayList<Symbol>();		// Alphabet to compare
	public static ArrayList<Token> tokenList 		 = new ArrayList<Token>();		// Alphabet to compare
	
	/**
	 * Compare the event consumed and the actual state of each automaton  
	 * @param event
	 * @throws BadTokenException 
	 */
	public static void processEvent(Event event) throws BadTokenException {
		
		String symbolId = getSymbolToUse(event.getText()).getText();
		
		// parameters will be null if the event does not accepted
		TransitionParameters parameters = Inspector.getTransitionFromEvent(event.getText(), symbolId);
		if(parameters != null){
			// Verify if the transition is possible
			for(Automaton automaton : automatonList){

				automaton.processTransition(symbolId, parameters);
				
			}
			
		}
		
	}
	
	/**
	 * Get the appropriate symbol given by eventText parameter
	 * @param eventText
	 * @return
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
	 * @return
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
	public static ArrayList<Automaton> getAutomatonList() {
		return automatonList;
	}

	public static void setAutomatonList(ArrayList<Automaton> automatonList) {
		BehaviorManager.automatonList = automatonList;
	}

	public static void addAutomaton(Automaton a) {
		// TODO Auto-generated method stub
		BehaviorManager.automatonList.add(a);
	}
	
	public static void addSymbol(Symbol s) {
		// TODO Auto-generated method stub
		BehaviorManager.symbolList.add(s);
	}

	public static ArrayList<Symbol> getSymbolList() {
		return symbolList;
	}

}
