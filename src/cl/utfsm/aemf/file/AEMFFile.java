package cl.utfsm.aemf.file;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import cl.utfsm.aemf.util.AEMFConfiguration;

public abstract class AEMFFile {

	private File filesDirectory;
	private String path;
	private DefaultHandler documentHandler;
	
	// Quantity of automatons monitoring 
	public static int AUTOMATON_ID = 0;
	// Quantity of automatons monitoring 
	public static String AUTOMATON_FILENAME = "";
	
	/**
	 * Constructor
	 * @throws  
	 * @throws Exception 
	 */
	public AEMFFile() throws IOException
	{
		// First, check if the behavior files directory exists
		setFilesDirectory(new File(AEMFConfiguration.AEMF_SOURCE_FILES_DIRECTORY));
		
		// If this directory does not exist, then abort all
		if(!this.getFilesDirectory().exists()) {
			throw new IOException("["+this.getFilesDirectory().getAbsolutePath()+"] " + AEMFConfiguration.BEHAVIOR_DIRECTORY_DOES_NOT_EXIST);
		}
	}
	
	/**
	 * Parse each file
	 */
	protected void parseFiles(String allowedExtension) {
		// Get all files from /AEMF_files
		File files[] = new File(AEMFConfiguration.AEMF_SOURCE_FILES_DIRECTORY + path).listFiles();
		
		Log.i(AEMFConfiguration.APPLICATION_TAG, "Starting reading files, "+files.length+" file(s) found.");
		for(File f : files)
		{
			if(!f.getName().contains(allowedExtension))
				continue;
			
			Log.i(AEMFConfiguration.APPLICATION_TAG, "Reading " + f.getName());
			try {
				AEMFFile.AUTOMATON_FILENAME = f.getName();
				parseFile(f);
				AEMFFile.AUTOMATON_ID++;
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Reads one .jff file and generates the respect BehaviorAutomaton object
	 * @param f
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	protected void parseFile(File f) throws ParserConfigurationException, SAXException, IOException {
		
		// Basic SAX parser
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
	
		parser.parse(f, getDocumentHandler());
		
	}
	
	/*
	 * Getters and setters
	 */
	/**
	 * @return
	 * @uml.property  name="documentHandler"
	 */
	public DefaultHandler getDocumentHandler() {
		return documentHandler;
	}
	/**
	 * @param  documentHandler
	 * @uml.property  name="documentHandler"
	 */
	public void setDocumentHandler(DefaultHandler docHandler) {
		documentHandler = docHandler;
	}
	/**
	 * @return
	 * @uml.property  name="filesDirectory"
	 */
	public File getFilesDirectory() {
		return filesDirectory;
	}
	/**
	 * @param  filesDirectory
	 * @uml.property  name="filesDirectory"
	 */
	public void setFilesDirectory(File filesDir) {
		filesDirectory = filesDir;
	}

	public String getSubdirectory() {
		return path;
	}

	public void setPath(String subdirectory) {
		this.path = subdirectory;
	}
}
