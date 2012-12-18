package cl.utfsm.aemf.file;

import cl.utfsm.aemf.token.Token;

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
public class TokenFile extends AEMFFile {


	ArrayList<Token> tokenList = new ArrayList<Token>();
	
	/**
	 * Constructor
	 * @param subdirectory
	 * @throws IOException
	 */
	public TokenFile(String path) throws IOException
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
			Token token;
			boolean readToken = false;
			boolean readValue = false;
			
			public void startDocument(){

			}
			
			// Receive notification of the start of an element
			public void startElement(String uri, String localName, String qName, Attributes attributes) {
				if(qName.equalsIgnoreCase("token")){
					readToken = true;
					token = new Token();
					token.setTokenId(attributes.getValue("name"));
					token.setTokenType(attributes.getValue("type"));
				}
				else if(qName.equalsIgnoreCase("value")){
					readValue = true;
				}
			}
			
			// Receive notification of character data inside an element
			public void characters(char ch[], int start, int length) throws SAXException {
				if(readToken && readValue){
					token.addAcceptedValue(new String(ch, start, length));
				}
			}
			
			// Receive notification of the end of an element
			public void endElement(String uri, String localName, String qName){
				if(qName.equalsIgnoreCase("token")){
					readToken = false;
					tokenList.add(token);
				}
				else if(qName.equalsIgnoreCase("value")){
					readValue = false;
				}
			}
			
			public void endDocument(){
				
			}
			
		});
	}

	/**
	 * Returns the generated token list read from source_path file
	 * @return automaton list
	 */
	public ArrayList<Token> getTokenList() {
		
		parseFiles(".tok");		
		return tokenList;
	}

}
