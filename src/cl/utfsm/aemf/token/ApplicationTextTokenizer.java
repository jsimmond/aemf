package cl.utfsm.aemf.token;

import cl.utfsm.aemf.manager.BehaviorManager;

public class ApplicationTextTokenizer extends TokenManager {

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
		Token t = BehaviorManager.getTokenById("application");
		for(String s : t.getValuesAcceptedList()){
			if(eventText.contains(s))
			{
				valueMatched = s;
				this.CURRENT_STATE = TokenManager.MATCH_FOUND;
				return true;
			}
		}
		
		this.CURRENT_STATE = TokenManager.MATCH_NOT_FOUND;
		return false;
	}

	@Override
	public String getValueMatched() {
		// TODO Auto-generated method stub
		return valueMatched;
	}

}
