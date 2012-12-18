package cl.utfsm.aemf.textevent;

import cl.utfsm.aemf.util.AEMFConfiguration;
import android.util.Log;

/**
 * This class ensures to read Automatons Files (.jff)
 * @author Sebastián Vásquez Morales
 *
 */

public class TextEvent {

	// Main fields of any event written
	private String date;
	private String time;
	private String PID;
	private String level;
	private String tag;
	private String application;
	private String text;


	/*
	 * Getters and Setters
	 */
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getService() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	// For debugging
	public void printEvent(){
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Event listener", "Event");
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Event listener", "> Date:" + this.date);
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Event listener", "> Time:" + this.time);
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Event listener", "> PID:" + this.PID);
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Event listener", "> Level:" + this.level);
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Event listener", "> Tag:" + this.tag);
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Event listener", "> Application:" + this.application);
		Log.i(AEMFConfiguration.APPLICATION_TAG+" - Event listener", "> Text:" + this.text );
	}
}
