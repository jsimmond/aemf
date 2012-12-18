package cl.utfsm.aemf.token;


public class ActivityTextTokenizer extends TextTokenizer {

	@Override
	public boolean isAccepted(Token t) {
		for(String s : t.getValuesAcceptedList()){
			if(eventText.contains(s))
			{
				valueMatched = s;
				this.CURRENT_STATE = TextTokenizer.MATCH_FOUND;
				return true;
			}
		}
		
		this.CURRENT_STATE = TextTokenizer.MATCH_NOT_FOUND;
		
		return false;
		
	}

	@Override
	public void setEventText(String str) {
		eventText = str;
	}

	@Override
	public int getCurrentState() {
		return CURRENT_STATE;
	}

	@Override
	public String getValueMatched() {
		return valueMatched;
	}

}
