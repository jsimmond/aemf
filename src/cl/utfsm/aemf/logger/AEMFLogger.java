package cl.utfsm.aemf.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.text.format.DateFormat;

/**
 * This class give some methods to write the final log
 * @author Sebastián Vásquez Morales
 *
 */
public class AEMFLogger {
	
	// Create new file or rewrite existing
	static File file;
	static FileOutputStream output;
			
	/**
	 * Opens a new log file to write
	 * @param streamPath
	 * @return 
	 */
	public static void openNewStream(String streamPath){
		file = new File(streamPath);
		
        try {
        	file.createNewFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Write some info to the AEMF Log
	 * @param stringToWrite
	 */
	public static void writeInfo(String stringToWrite) {
		long dtMili = System.currentTimeMillis();
		
		Date date = new Date(dtMili);
		String timestamp;
		if(dtMili%1000 < 100)
			timestamp = DateFormat.format("hh:mm:ss", date) + ".0" +dtMili%1000+ " - " ;
		else
			timestamp = DateFormat.format("hh:mm:ss", date) + "." +dtMili%1000+ " - " ;
		
		try {
			output = new FileOutputStream(file, true);
			output.write((timestamp + stringToWrite + "\n").getBytes());
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Write some info to the AEMF Log
	 * @param stringToWrite
	 */
	public static void write(String stringToWrite) {
		long dtMili = System.currentTimeMillis();
		
		Date date = new Date(dtMili);
		String timestamp;
		if(dtMili%1000 < 100)
			timestamp = DateFormat.format("hh:mm:ss", date) + ".0" +dtMili%1000+ " - " ;
		else
			timestamp = DateFormat.format("hh:mm:ss", date) + "." +dtMili%1000+ " - " ;
		
		try {
			output = new FileOutputStream(file, true);
			output.write(("" + timestamp + stringToWrite + "\n").getBytes());
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
