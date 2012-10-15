package cl.utfsm.aemf.util;

public class ServicesListened {
	
	//TODO Hacer mas modular, que lea de un archivo de texto estos nombres de servicios
	public static final String ACTIVITY_MANAGER = "ActivityManager";
	public static final String INPUT_READER = "InputReader";
	public static final String INPUT_DISPATCHER = "InputDispatcher";
	public static final String BATTERY_SERVICE = "BatteryService";
	public static final String WIFI_MONITOR = "WifiMonitor";
	
	
	// The List of the Android Services to be monitored
	public static final String tagArrayListened[] =
			{ 
				ACTIVITY_MANAGER,
				INPUT_READER,
				INPUT_DISPATCHER,
				BATTERY_SERVICE,
				WIFI_MONITOR
			}; 
	

	
	
}
