package cl.utfsm.aemf.behavior;

/**
 * Behavior class: This class allow you to load a behavior file.  
 * @author sebastian
 *
 */

import java.util.List;

import cl.utfsm.aemf.behaviorStates.BehaviorState;
import cl.utfsm.aemf.util.Globals;


public class Behavior {

	/*
	 * General fields 
	 */
	private List<BehaviorFile> behaviorFiles;	//the path of the behavior definition file
	private int currentState;					//current state of this behavior (see below)
	private List<BehaviorState> states;			//Behaviors actions list (all of them)
	
	
	/**
	 * Constructors
	 */
	public Behavior()
	{
		
	}
	
	
	/*
	 * PUBLIC METHODS
	 */
	
	/**
	 * Reads the behavior file specified by <i>source</i> parameter 
	 * @param source
	 * @return true if the file load was OK 
	 * @throws Exception 
	 */
	public void readBehaviorFile(String source) throws Exception
	{
		/*
		 * Analyzing each file
		 */
		for(BehaviorFile file : behaviorFiles)
		{
			// Check his structure
			if(file.checkFileStructure())
			{

			}
			else
			{
				throw new Exception(Globals.BEHAVIOR_FILE_BAD_STRUCTURE);
			}
		
		}

		
		
		
	}
	
	
	/*
	 * PRIVATE METHODS
	 */
	private boolean checkBehaviorFileStructure()
	{
		/**
		 * TODO Definir este metodo, ... o sacarlo?
		 */
		return true;
	}

	
	/*
	 * GETTERS AND SETTERS
	 */
	
	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}
	



	
	
}
