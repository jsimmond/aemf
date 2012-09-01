package cl.utfsm.aemf.behaviorListener;

/**
 * This Class represents any event listened by LogCat
 * @author sebastian
 *
 */

public class LogcatEvent {
	
	private int level;
	private String time;
	private int PID;
	private String application;
	private String TAG;
	private String text;
	
	public final static int LEVEL_VERBOSE 	= 0;
	public final static int LEVEL_DEBUG		= 1;
	public final static int LEVEL_INFO		= 2;
	public final static int LEVEL_WARNING 	= 3;
	public final static int LEVEL_ERROR		= 4;
	public final static int LEVEL_FATAL		= 5;
	public final static int LEVEL_SILENT	= 6;
	
	/**
	 * Constructor
	 * @param l
	 * @param t
	 * @param p
	 * @param app
	 * @param tag
	 * @param tex
	 */
	public LogcatEvent(int l, String t, int p, String app, String tag, String tex)
	{
		setLevel(l);
		setTime(t);
		setPID(p);
		setApplication(app);
		setTAG(tag);
		setText(tex);
	}
	
	/**
	 * GETTERS AND SETTERS
	 */
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getTAG() {
		return TAG;
	}
	public void setTAG(String tAG) {
		TAG = tAG;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
