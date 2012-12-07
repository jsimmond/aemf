package cl.utfsm.aemf.token;

public class ProcessIdTextTokenizer extends TextTokenizer {

	@Override
	public void setEventText(String str) {
		eventText = str;
	}

	@Override
	public int getCurrentState() {
		// TODO Auto-generated method stub
		return CURRENT_STATE;
	}

	/**
	 * Verify if the eventText matches on this ApplicationToken
	 */
	@Override
	public boolean isAccepted() {
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
		// TODO Auto-generated method stub
		return valueMatched;
	}

}
