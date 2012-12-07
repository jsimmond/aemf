package cl.utfsm.aemf.event;

/**
 * Mandatory methods to be implemented by a event listener
 * @author Sebastián Vásquez Morales
 *
 */
public interface AutomatonListener {
	public void handleAutomatonEvent(AutomatonEvent e);
}
