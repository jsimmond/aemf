package cl.utfsm.aemf.token;

public abstract class TokenManager {
	
	public String eventText;
	public int CURRENT_STATE = SEARCHING_VALUE;
	
	// Some constants
	public static final int SEARCHING_VALUE = 0;
	public static final int MATCH_FOUND 	= 1;
	public static final int MATCH_NOT_FOUND = 2;
	
	public abstract boolean isAccepted();
	public abstract void setEventText(String string);
	public abstract int getCurrentState();
	
	public String valueMatched;
	public abstract String getValueMatched();
}
