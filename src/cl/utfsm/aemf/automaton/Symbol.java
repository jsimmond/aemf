package cl.utfsm.aemf.automaton;

import java.util.ArrayList;
import java.util.Map.Entry;

import android.util.Log;
import cl.utfsm.aemf.util.Globals;

public class Symbol {
	
	
	private String 	id;
	private int 	level;
	private String 	tag;
	private String 	text;
	private TransitionParameters params;
	
	/**
	 * Constructor
	 */
	public Symbol(){
		params = new TransitionParameters();
	}
	
	/*
	 * Getters and setters
	 */
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public void setTransiitionParameters(TransitionParameters tp) {
		this.params = tp;
	}
	
	
	// For debugging
	public void printSymbol(){
		Log.i(Globals.APPLICATION_TAG+" - Symbol reading", "SYMBOL");
		Log.i(Globals.APPLICATION_TAG+" - Symbol reading", "> id: " + this.getId());
		Log.i(Globals.APPLICATION_TAG+" - Symbol reading", "> tag: " + this.getTag());
		Log.i(Globals.APPLICATION_TAG+" - Symbol reading", "> text: " + this.getText());
		Log.i(Globals.APPLICATION_TAG+" - Symbol reading", "> params: ");
		for(Entry<String, ArrayList<String>> s : params.getHashMap().entrySet()){
			for(String val : s.getValue()){
				Log.i(Globals.APPLICATION_TAG+" - Symbol reading", "> > " + s.getKey() +" : "+ val);
			}
			

		}
		
	}
	

	
	
}
