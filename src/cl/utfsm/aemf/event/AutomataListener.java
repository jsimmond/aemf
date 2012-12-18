package cl.utfsm.aemf.event;

/**
 * Mandatory methods to be implemented by a event listener
 * @author Sebastián Vásquez Morales
 *
 */
public interface AutomataListener {
	public void handleAutomatonEvent(AutomataEvent e);
}
