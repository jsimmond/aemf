package cl.utfsm.aemf.event;

import cl.utfsm.aemf.util.Globals;
import android.util.Log;

/**
 * This class ensures to read Automatons Files (.jff)
 * @author Sebastián Vásquez Morales
 *
 */

public class Event {

	// Main fields of any event written
	/**
	 * @uml.property  name="date"
	 */
	private String date;
	/**
	 * @uml.property  name="time"
	 */
	private String time;
	/**
	 * @uml.property  name="pID"
	 */
	private String PID;
	/**
	 * @uml.property  name="level"
	 */
	private String level;
	/**
	 * @uml.property  name="tag"
	 */
	private String tag;
	/**
	 * @uml.property  name="application"
	 */
	private String application;
	/**
	 * @uml.property  name="text"
	 */
	private String text;


	/*
	 * Getters and Setters
	 */
	/**
	 * @return
	 * @uml.property  name="date"
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param  date
	 * @uml.property  name="date"
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return
	 * @uml.property  name="time"
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param  time
	 * @uml.property  name="time"
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return
	 * @uml.property  name="pID"
	 */
	public String getPID() {
		return PID;
	}

	/**
	 * @param  pID
	 * @uml.property  name="pID"
	 */
	public void setPID(String pID) {
		PID = pID;
	}

	/**
	 * @return
	 * @uml.property  name="level"
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param  level
	 * @uml.property  name="level"
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	public String getService() {
		return tag;
	}

	/**
	 * @param  tag
	 * @uml.property  name="tag"
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return
	 * @uml.property  name="application"
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * @param  application
	 * @uml.property  name="application"
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * @return
	 * @uml.property  name="text"
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param  text
	 * @uml.property  name="text"
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	// For debugging
	public void printEvent(){
		Log.i(Globals.APPLICATION_TAG+" - Event listener", "Event");
		Log.i(Globals.APPLICATION_TAG+" - Event listener", "> Date:" + this.date);
		Log.i(Globals.APPLICATION_TAG+" - Event listener", "> Time:" + this.time);
		Log.i(Globals.APPLICATION_TAG+" - Event listener", "> PID:" + this.PID);
		Log.i(Globals.APPLICATION_TAG+" - Event listener", "> Level:" + this.level);
		Log.i(Globals.APPLICATION_TAG+" - Event listener", "> Tag:" + this.tag);
		Log.i(Globals.APPLICATION_TAG+" - Event listener", "> Application:" + this.application);
		Log.i(Globals.APPLICATION_TAG+" - Event listener", "> Text:" + this.text );
	}
}
