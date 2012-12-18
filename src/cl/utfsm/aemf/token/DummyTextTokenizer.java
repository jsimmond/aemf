package cl.utfsm.aemf.token;

public class DummyTextTokenizer extends TextTokenizer {

	@Override
	public boolean isAccepted(Token t) {
		return true;
	}

	@Override
	public void setEventText(String string) {
		eventText = string;
	}

	@Override
	public int getCurrentState() {
		return 0;
	}

	@Override
	public String getValueMatched() {
		return null;
	}


}
