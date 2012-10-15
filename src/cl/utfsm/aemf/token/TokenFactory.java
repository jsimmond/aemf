package cl.utfsm.aemf.token;

public class TokenFactory {
	
	
	/**
	 * Generate the appropriate tokenizer
	 * @param tokenType
	 * @return
	 * @throws BadTokenException 
	 */
	public static TokenManager createTokenizer(String tokenType) throws BadTokenException{
		
		if(tokenType.equalsIgnoreCase("dummy")){
			return new DummyTextTokenizer();
		}
		else if(tokenType.equalsIgnoreCase("application")){
			return new ApplicationTextTokenizer();
		}
		else if(tokenType.equalsIgnoreCase("activity")){
			return new ActivityTextTokenizer();
		}
		else {
			throw new BadTokenException();
		}
	}
}
