package cl.utfsm.aemf.token;

public class ProcessIdTextTokenizer extends TextTokenizer {

	@Override
	public void setEventText(String str) {
		eventText = str;
	}

	@Override
	public int getCurrentState() {
		return CURRENT_STATE;
	}

	/**
	 * Verify if the eventText matches on this ApplicationToken
	 */
	@Override
	public boolean isAccepted(Token t) {
		try {
			Integer.parseInt(eventText);
			valueMatched = eventText;
			this.CURRENT_STATE = MATCH_FOUND;
			return true;
		}
		catch(NumberFormatException e){
			this.CURRENT_STATE = MATCH_NOT_FOUND;
			return false;
		}
	}

	@Override
	public String getValueMatched() {
		return valueMatched;
	}

}
