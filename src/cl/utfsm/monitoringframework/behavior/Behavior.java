package cl.utfsm.monitoringframework.behavior;

/**
 * Behavior class: This class allow you to load a behavior file.  
 * @author sebastian
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import cl.utfsm.monitoringframework.util.Globals;


public class Behavior {

	/*
	 * General fields 
	 */
	private String sourceFile;		//the path of the behavior definition file
	private int currentState;		//current state of this behavior (see below)
	
	/*
	 * Constants
	 */
	public static int NOT_CREATED_STATE = -1;
	public static int LOAD_STATE 		=  0;
	public static int RUNNING_STATE 	=  1;
	public static int FINISH_STATE 		=  2;
	public static int FAULT_STATE 		=  3;
	public static int INFO_STATE 		=  4;

	
	/**
	 * Constructors
	 */
	public Behavior()
	{
		sourceFile = "";
		currentState = Behavior.NOT_CREATED_STATE;
	}
	
	public Behavior(String source)
	{
		sourceFile = source;
		currentState = Behavior.NOT_CREATED_STATE;
	}
	
	
	/**
	 * PUBLIC METHODS
	 */
	
	/**
	 * Reads the behavior file specified by <i>source</i> parameter 
	 * @param source
	 * @return true if the file load was OK 
	 * @throws Exception 
	 */
	public boolean readBehaviorFile(String source) throws Exception
	{
		//Definition file was not setted
		if(sourceFile == "")
		{
			throw new IOException(Globals.NO_ESPECIFIED_BEHAVIOR_FILE);
		}
		else
		{
			File behaviorFile = new File(source);
			
			//Does the definition file exists?
			if(!behaviorFile.exists())
			{
				throw new FileNotFoundException(Globals.BEHAVIOR_FILE_DOES_NOT_EXIST);
			}
			else
			{
				//If it exists, check his structure
				if(checkBehaviorFileStructure())
				{
					/**
					 * TODO Rellenar campos internos de esta clase, aca ya se ha validado todo
					 */
					return true;
				}
				else
				{
					throw new Exception(Globals.BEHAVIOR_FILE_BAD_STRUCTURE);
				}
			}
		}
		
	}
	
	
	/**
	 * PRIVATE METHODS
	 */
	private boolean checkBehaviorFileStructure()
	{
		/**
		 * TODO Definir este metodo, ... o sacarlo?
		 */
		return true;
	}
	



	
	
}
