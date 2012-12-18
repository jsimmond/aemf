package cl.utfsm.aemf.automata;

import java.util.ArrayList;

import android.util.Log;
import cl.utfsm.aemf.util.AEMFConfiguration;

public class Symbol {
	
	
	private String 	id;
	private int 	level;
	private String 	tag;
	private String 	text;
	private ArrayList<String> params;
	private boolean requirePID = false;
	
	/**
	 * Constructor
	 */
	public Symbol(){
		params = new ArrayList<String>();
	}
	
	/*
	 * Getters and setters
	 */
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
		
		// Put here if the token read require a PID
		if(id.equals("start_intent")){
			requirePID = true;
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setParameters(ArrayList<String> tp) {
		this.params = tp;
	}
	
	public boolean isPIDRequired(){
		return requirePID;
	}
	
	
	// For debugging
	public void printSymbol(){
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Symbol reading", "SYMBOL");
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Symbol reading", "> id: " + this.getId());
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Symbol reading", "> tag: " + this.getTag());
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Symbol reading", "> text: " + this.getText());
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Symbol reading", "> params: ");
			for(String val : params){
				Log.i(AEMFConfiguration.APPLICATION_TAG+" - Symbol reading", "> > " + val);
			}
			
	}
	

	
	
}
