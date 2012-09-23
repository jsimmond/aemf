package cl.utfsm.aemf.event;

public class Event {

	// Main fields of any event written
	private String date;
	private String time;
	private String PID;
	private String level;
	private String service;
	private String application;
	private String text;

	// Some level types
	public final static int LEVEL_VERBOSE = 0;
	public final static int LEVEL_DEBUG = 1;
	public final static int LEVEL_INFO = 2;
	public final static int LEVEL_WARNING = 3;
	public final static int LEVEL_ERROR = 4;
	public final static int LEVEL_FATAL = 5;
	public final static int LEVEL_SILENT = 6;

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
		return service;
	}

	public void setService(String service) {
		this.service = service;
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
}
