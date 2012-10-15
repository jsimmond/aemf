package cl.utfsm.aemf.util;

import android.os.Environment;

/**
 * Global Class: Provides a set of numerous static values
 * @author sebastian
 *
 */
public class Globals {
	
	
	/**
	 * Behavior error strings
	 */
	public static final String NO_ESPECIFIED_BEHAVIOR_FILE = "no behavior file specified";
	public static final String BEHAVIOR_DIRECTORY_DOES_NOT_EXIST = "the behavior directory specified does not exist, please create a folder called 'AEMF_files' (without quotes) in the root of your sdcard/";
	public static final String BEHAVIOR_FILE_DOES_NOT_EXIST = "the behavior file specified does not exist";
	public static final String BEHAVIOR_FILE_BAD_STRUCTURE = "the behavior file contains a bad structure, check it before load this resource";
	
	// Application tag
	public static final String APPLICATION_TAG = "AEMF";
	public static final String ERROR_AT_READ_LOG = "an error occurred while AEMF reading the buffer log";
	
	// Specific source paths
	public static final String AEMF_SOURCE_FILES_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AEMF_files/";	//points to /mnt/sdcard/AEMF_files/
	
	// The application to be monitored
	public static String APPLICATION_ID_TO_BE_MONITORED = "cl.vasquez.MainPackage";
	public static int PROCESS_ID_TO_BE_MONITORED = 0;
	
	
	
}
