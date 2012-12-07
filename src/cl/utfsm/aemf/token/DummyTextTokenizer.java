package cl.utfsm.aemf.token;

public class DummyTextTokenizer extends TextTokenizer {

	@Override
	public boolean isAccepted() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setEventText(String string) {
		eventText = string;
	}

	@Override
	public int getCurrentState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getValueMatched() {
		// TODO Auto-generated method stub
		return null;
	}


}
