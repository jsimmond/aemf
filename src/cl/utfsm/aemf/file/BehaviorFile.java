package cl.utfsm.aemf.file;

import cl.utfsm.aemf.automata.Automata;
import cl.utfsm.aemf.automata.State;
import cl.utfsm.aemf.automata.Transition;

import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;


/**
 * Provide methods to manipulate the behavior files
 * @author Sebastián Vásquez Morales
 *
 */
public class BehaviorFile extends AEMFFile {

	/**
	 * @uml.property  name="automatonList"
	 */
	ArrayList<Automata> automatonList = new ArrayList<Automata>();
	
	/**
	 * Constructor
	 * @param subdirectory
	 * @throws IOException
	 */
	public BehaviorFile(String path) throws IOException
	{
		super();
		setPath(path);
		generateDefaultHandler();
	}
	
	/**
	 * Generate the adhoc DefaultHandler to parse de .jff file
	 */
	private void generateDefaultHandler() {
		
		// Implements a generic DefaultHandler to handle (doh!) the file reading
		setDocumentHandler(new DefaultHandler()
		{ 
			Automata automaton;
			State state;
			Transition transition;
			
			boolean readState = false;
			boolean readTransition = false;
			
			boolean readLabel = false;
			boolean readInitial = false;
			boolean readFinal = false;
			boolean readFrom  = false;
			boolean readTo    = false;
			boolean readRead  = false;
			
			public void startDocument(){
				// before start to reading the .jff file, create a new void automaton object
				automaton = new Automata();
			}
			
			// Receive notification of the start of an element
			public void startElement(String uri, String localName, String qName, Attributes attributes) {
				
				/*
				 * Reading a state
				 */
				if(qName.equalsIgnoreCase("state")) {
					state = new State();
					state.setId(Integer.parseInt(attributes.getValue("id")));
					state.setName(attributes.getValue("name"));
	
					readState = true;
				}
				if(qName.equalsIgnoreCase("label"))	{
					readLabel = true;
				}
				if(qName.equalsIgnoreCase("initial")) { 
					readInitial = true;
				}
				if(qName.equalsIgnoreCase("final")) { 
					readFinal = true;
				}
				
				/*
				 * Reading a transition
				 */
				if(qName.equalsIgnoreCase("transition")) {
					// Reading a transition
					transition = new Transition();
					readTransition = true;
				}
				if(qName.equalsIgnoreCase("from")) {
					readFrom = true;
				}
				if(qName.equalsIgnoreCase("to")) {
					readTo = true;
				}
				if(qName.equalsIgnoreCase("read")) {
					readRead = true;
				}
			}
			
			// Receive notification of character data inside an element
			public void characters(char ch[], int start, int length) throws SAXException {
				
				/*
				 * Reading characters from the state
				 */
				if(readState)
				{
					
					if(readLabel) { 
						state.setLabel(new String(ch, start, length));
						readLabel = false;
					}
					else if(readInitial) {
						state.setStateType(State.INITIAL_STATE);
						readInitial = false;
					}
					else if(readFinal) {
						state.setStateType(State.FINAL_STATE);
						readFinal = false;
					}
					
				}
				
				
				/*
				 * Reading characters from the transition
				 */
				if(readTransition) 
				{
					if(readFrom)
					{
						int index = Integer.parseInt(new String(ch, start, length));
						transition.setFromState(index);
						readFrom = false;
					}
					
					else if(readTo) {
						int index = Integer.parseInt(new String(ch, start, length));
						State newState = automaton.getStates().get(index);
						transition.setToState(newState);
						readTo = false;
					}
					
					else if(readRead) 
					{
						transition.config(new String(ch, start, length));
						readRead = false;
					}
					
				}
			}
			
			// Receive notification of the end of an element
			public void endElement(String uri, String localName, String qName){
				if(qName.equalsIgnoreCase("state")) {
					// End Reading a state
					readState = false;
					
					// If this state is neither Initial nor Final, then it is Intermediate
					if(state.getStateType() != State.INITIAL_STATE && state.getStateType() != State.FINAL_STATE)
						state.setStateType(State.INTERMEDIATE_STATE);
					
					//Add this state to the new automaton 
					automaton.addState(state);
				}
				if(qName.equalsIgnoreCase("transition")) {
					// End Reading a state
					automaton.getStates().get(transition.getFromState()).addTransition(transition);
					readTransition = false;
				}
			}
			
			public void endDocument(){
				// Add the automaton
				automaton.setInitialState();
				
				automaton.setId(AEMFFile.AUTOMATON_ID);
				automaton.setFileName(AEMFFile.AUTOMATON_FILENAME);
				
				automatonList.add(automaton);
			}
			
		});
	}

	/**
	 * Returns the generated automaton read from source_path file
	 * @return automaton list
	 */
	public ArrayList<Automata> getAutomatonList() {
		
		parseFiles(".jff");		
		return automatonList;
	}

}
