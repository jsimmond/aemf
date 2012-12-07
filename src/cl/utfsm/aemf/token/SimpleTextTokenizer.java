package cl.utfsm.aemf.token;

public class SimpleTextTokenizer extends TextTokenizer {

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
		
		if(!eventText.equals("") || !eventText.contains(" ")) {
			this.CURRENT_STATE = TextTokenizer.MATCH_FOUND;
			valueMatched = eventText;
			return true;
		}
		else
			return false;
	}

	@Override
	public String getValueMatched() {
		// TODO Auto-generated method stub
		return valueMatched;
	}

}
