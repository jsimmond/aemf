package cl.utfsm.aemf.file;

import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cl.utfsm.aemf.automaton.Symbol;


/**
 * Provide methods to manipulate the alphabet file
 * @author Sebastián Vásquez Morales
 *
 */
public class AlphabetFile extends AEMFFile {
	
	/**
	 * @uml.property  name="alphabet"
	 */
	ArrayList<Symbol> alphabet = new ArrayList<Symbol>();
	
	/**
	 * Constructor
	 * @throws Exception 
	 */
	public AlphabetFile(String path) throws IOException
	{
		super();
		setPath(path);
		generateDefaultHandler();
	}
	
	/**
	 * Generate the adhoc DefaultHandler to parse THe .XML file
	 */
	private void generateDefaultHandler() {
		
		// Implements a generic DefaultHandler to handle (doh!) the file reading
		setDocumentHandler(new DefaultHandler()
		{ 
			
			// Some flags
			boolean readTransition = false;
			boolean readId 		= false;
			boolean readTag 	= false;
			boolean readText 	= false;
			
			boolean readParam  = false;
			
			// The Symbol object
			Symbol symbol;
			ArrayList<String> tp;
			
			public void startDocument(){
			}
			
			public void startElement(String uri, String localName, String qName, Attributes attributes){
				
				if(qName.equalsIgnoreCase("transition")){
					readTransition = true;
					symbol = new Symbol();
					tp = new ArrayList<String>();
				}
	
				else if(qName.equalsIgnoreCase("id")){
					readId = true;
				}
				
				else if(qName.equalsIgnoreCase("param")){
					readParam = true;
				}
				
				else if(qName.equalsIgnoreCase("tag")){
					readTag = true;
				}
				
				else if(qName.equalsIgnoreCase("text")){
					readText = true;
				}
				else return;
			}
			
			public void characters(char ch[], int start, int length) throws SAXException {

				if(readTransition) 
				{	
					if(readId)
					{
						symbol.setId(new String(ch, start, length));
						readId = false;
					}
					else if(readParam){
						tp.add(new String(ch, start, length));
						readParam = false;
					}
					
					else if(readTag)
					{
						symbol.setTag(new String(ch, start, length));
						readTag = false;
					}
					
					else if(readText)
					{
						symbol.setText(new String(ch, start, length));
						readText = false;
					}
					
				}
			}
			
			public void endElement(String uri, String localName, String qName){
				
				if(qName.equalsIgnoreCase("transition")) {
					symbol.setParameters(tp);
					alphabet.add(symbol);
					readTransition = false;
				}
			}
			
			public void endDocument(){
			}
			
		});
	}
	
	/**
	 * Parse the files an then returns the alphabet
	 * @return the alphabet parsed
	 */
	public ArrayList<Symbol> getSymbolList(){
		
		parseFiles(".xml");
		return alphabet;
	}
	
}
