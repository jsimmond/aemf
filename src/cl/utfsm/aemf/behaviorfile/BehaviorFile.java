package cl.utfsm.aemf.behaviorfile;

import android.util.Log;

import cl.utfsm.aemf.behaviormanager.BehaviorAutomaton;
import cl.utfsm.aemf.behaviormanager.BehaviorState;
import cl.utfsm.aemf.behaviormanager.BehaviorTransition;
import cl.utfsm.aemf.util.Globals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;


/**
 * Provide methods to manipulate the behavior files
 * @author sebastian
 *
 */
public class BehaviorFile {

	ArrayList<BehaviorAutomaton> automatonList = new ArrayList<BehaviorAutomaton>();
	File behaviorFilesDirectory;	
	
	/**
	 * Constructor
	 * @throws Exception 
	 */
	public BehaviorFile() throws IOException
	{
		// First, check if the behavior files directory exists
		behaviorFilesDirectory = new File(Globals.AEMF_SOURCE_FILES_DIRECTORY);
		
		// If this directory does not exist, then abort all
		if(!behaviorFilesDirectory.exists()) {
			throw new IOException("["+behaviorFilesDirectory.getAbsolutePath()+"] " + Globals.BEHAVIOR_DIRECTORY_DOES_NOT_EXIST);
		}
		
	}

	
	/**
	 * Returns the generated automaton read from source_path file
	 * @return
	 */
	public ArrayList<BehaviorAutomaton> getAutomatonList() {
			
		// Get all files from /AEMF_files
		File files[] = new File(Globals.AEMF_SOURCE_FILES_DIRECTORY).listFiles();
		
		Log.i(Globals.APPLICATION_TAG, "Starting reading files, "+files.length+" file(s) found.");
		for(File f : files)
		{
			Log.i(Globals.APPLICATION_TAG, "Reading " + f.getAbsolutePath());
			try {
				parseAutomaton(f);
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return automatonList;
	}

	/**
	 * Reads one .jff file and generates the respect BehaviorAutomaton object
	 * @param f
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void parseAutomaton(File f) throws ParserConfigurationException, SAXException, IOException {
		
		// Basic SAX parser
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		
		// Implements a generic DefaultHandler to handle (doh!) the file reading
		DefaultHandler documentHandler = new DefaultHandler()
		{ 
			BehaviorAutomaton automaton;
			BehaviorState state;
			BehaviorTransition transition;
			
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
				automaton = new BehaviorAutomaton();
			}
			
			// Receive notification of the start of an element
			public void startElement(String uri, String localName, String qName, Attributes attributes) {
				
				/*
				 * Reading a state
				 */
				if(qName.equalsIgnoreCase("state")) {
					state = new BehaviorState();
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
					transition = new BehaviorTransition();
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
				if(readState && readLabel) {
					state.setLabel(new String(ch, start, length));
					readLabel = false;
				}
				if(readState && readInitial) {
					state.setStateType(BehaviorState.INITIAL_STATE);
					readInitial = false;
				}
				if(readState && readFinal) {
					state.setStateType(BehaviorState.FINAL_STATE);
					readFinal = false;
				}
				
				
				/*
				 * Reading characters from the transition
				 */
				if(readTransition && readFrom) {
					int index = Integer.parseInt(new String(ch, start, length));
					transition.setFromState(index);
					
					readFrom = false;
				}
				if(readTransition && readTo) {
					int index = Integer.parseInt(new String(ch, start, length));
					BehaviorState newState = automaton.getStates().get(index);
					transition.setToState(newState);
					
					readTo = false;
				}
				if(readTransition && readRead) {
					transition.setTransitionText(new String(ch, start, length));
					readRead = false;
				}
			}
			
			// Receive notification of the end of an element
			public void endElement(String uri, String localName, String qName){
				if(qName.equalsIgnoreCase("state")) {
					// End Reading a state
					readState = false;
					
					// If this state is neither Initial nor Final, then it is Intermediate
					if(state.getStateType() != BehaviorState.INITIAL_STATE && state.getStateType() != BehaviorState.FINAL_STATE)
						state.setStateType(BehaviorState.INTERMEDIATE_STATE);
					
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
				automatonList.add(automaton);	
			}
			
		};
		
		parser.parse(f, documentHandler);
		
		
	}
	
	
	
}
