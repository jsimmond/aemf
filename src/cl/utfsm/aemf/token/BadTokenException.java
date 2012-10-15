package cl.utfsm.aemf.token;

public class BadTokenException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadTokenException() {
		// TODO Auto-generated constructor stub
		super("The token read was not valid");
	}
}
