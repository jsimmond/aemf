package cl.utfsm.aemf.token;

public class TokenFactory {
	
	
	/**
	 * Generate the appropriate tokenizer
	 * @param tokenType
	 * @return
	 * @throws BadTokenException 
	 */
	public static TextTokenizer createTokenizer(String tokenType) throws BadTokenException{
		
		if(tokenType.equalsIgnoreCase("dummy")){
			return new DummyTextTokenizer();
		}
		else if(tokenType.equalsIgnoreCase("application")){
			return new ApplicationTextTokenizer();
		}
		else if(tokenType.equalsIgnoreCase("activity")){
			return new ActivityTextTokenizer();
		}
		else if(tokenType.equalsIgnoreCase("simple")){
			return new SimpleTextTokenizer();
		}
		else if(tokenType.equalsIgnoreCase("process_id")){
			return new ProcessIdTextTokenizer();
		}
		else {
			throw new BadTokenException();
		}
	}
}
