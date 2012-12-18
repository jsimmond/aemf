package cl.utfsm.aemf.manager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cl.utfsm.aemf.automata.Symbol;
import cl.utfsm.aemf.automata.TransitionConfiguration;
import cl.utfsm.aemf.token.BadTokenException;
import cl.utfsm.aemf.token.DummyTextTokenizer;
import cl.utfsm.aemf.token.ProcessIdTextTokenizer;
import cl.utfsm.aemf.token.TokenFactory;
import cl.utfsm.aemf.token.TextTokenizer;

/**
 * This class compare a Event Object reader by the BehaviorListener with
 * the alphabet accepted by the automatons. 
 * @author Sebastián Vásquez Morales
 *
 */
public class Inspector {
	
	/**
	 * Analyze the event received and compare it with the appropiatre symbol
	 * @param eventText
	 * @param symbolText
	 * @return
	 * @throws BadTokenException 
	 * @throws Exception
	 */
	public static TransitionConfiguration getTransitionFromEvent(String eventText, Symbol symbol) throws BadTokenException{
				
		// This object will be filled with data
		// while the string analysis is performed.
		// This data will help to make the correspondent 
		// transitions to each automatons.
		TransitionConfiguration transition = new TransitionConfiguration();
		
		boolean withToken = false;	//Flag to determinate if the inspector is inside a "{% ... %}" string
		boolean usingDummyToken = false;
		
		String eventArray[]  = eventText.split(" ");
		String symbolArray[] = symbol.getText().split(" ");
		
		// Process all the String
		int i = 0, j = 0;
		
		while(i < eventArray.length){
			
			if(eventArray[i].equals(symbolArray[j])){
				i++;
				j++;
				usingDummyToken = false;
				continue;
			}
			
			// symbolArray[i] is a token
			// eventArray[j]  is a string
			if(isToken(symbolArray[j]))
			{
				withToken = true;
				ArrayList<String> list = getTokens(symbolArray[j]);
				for(String token : list){
					// Generate the manager
					TextTokenizer tokenManager = TokenFactory.createTokenizer(token);
					
					// While stay inside a token
					while(withToken){
						
						tokenManager.setEventText(eventArray[i]); 
						
						// The dummy token can consume more than one string
						if(tokenManager instanceof DummyTextTokenizer) {
							usingDummyToken = true;
							break;
						}
						// token is accepted by the automaton?
						else if(tokenManager.isAccepted(BehaviorManager.getTokenById(token)))
						{
							if(tokenManager instanceof ProcessIdTextTokenizer){
								usingDummyToken = false;
								transition.setPID(Integer.parseInt(tokenManager.getValueMatched()));
							}
							else{
								usingDummyToken = false;
								
								// Sets the {%token%} and his value
								transition.addParameter(token, tokenManager.getValueMatched());
								
								// Removes the found text and other special characters
								eventArray[i] = eventArray[i].replace(String.valueOf(tokenManager.getValueMatched()), "");
								eventArray[i] = eventArray[i].replace("/", "");
							}
							
							break;
						}
						else
						{
							// If we are using a dummy Token
							if(usingDummyToken)
							{
								i++;
								continue;
							}
							
							// If a value was found successful, then continue analizing
							if(tokenManager.getCurrentState() == TextTokenizer.MATCH_FOUND)
							{
								continue;
							}
							else if(tokenManager.getCurrentState() == TextTokenizer.MATCH_NOT_FOUND)
							{
								if(usingDummyToken) {
									i++;
									continue;
								}
								
								return null;
							}
							
						
						}
					}
				}
				
				if(j < symbolArray.length - 1)
					j++;
				if(!usingDummyToken)
					i++;
				else
					if(i == eventArray.length -1 && j == symbolArray.length -1)
						break;
					
			}
			else if(usingDummyToken)
			{
				i++;
			}
			
		}
		
		return transition;
	}
	
	
	// Private methods
	
	/**
	 * Return if the given string is a token
	 * @param str
	 * @return
	 */
	private static boolean isToken(String str){
		if(str.contains("{%") && str.contains("%}"))
			return true;
		return false;
	}
	/**
	 * Get the tokens strings
	 * @param str
	 * @return
	 */
	private static ArrayList<String> getTokens(String str) {
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		Pattern p = Pattern.compile("\\{\\%[a-zA-Z_]+\\%\\}");
		Matcher m = p.matcher(str);
		@SuppressWarnings("unused")
		boolean b = false;
		while(b = m.find()){
			tokens.add(m.group().split("%")[1]);
		}
		
		return tokens;
	}
	
	
	
}
