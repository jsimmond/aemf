package cl.utfsm.aemf.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;


/**
 * Util Class: Privides a set of numerous static utilities
 * 
 * @author sebastian
 * 
 */
public class Util {

	public static int getProcessId(String applicationId) {
		String line = "";
		
		// Was the PID calculated before?
		if(AEMFConfiguration.APPLICATION_PID_MONITORED < 0)
			try {
				Process process = Runtime.getRuntime().exec("ps");
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
	
				while ((line = br.readLine()) != null) {
					String s[] = line.split(" ");
					if(s[s.length-1].contains(applicationId)){
						AEMFConfiguration.APPLICATION_PID_MONITORED = Integer.valueOf(s[4]);
						return AEMFConfiguration.APPLICATION_PID_MONITORED;
					}
				}
			} catch (Exception e) {
				AEMFConfiguration.APPLICATION_PID_MONITORED = -1;
			}
		

		return AEMFConfiguration.APPLICATION_PID_MONITORED;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isServiceRunning(Context c) {
	    ActivityManager manager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	    	System.out.println(service.service.getClassName() +"-"+c.getPackageName());
	        if (c.getPackageName().equals(service.process)) {
	        	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>" + service.clientPackage);
	            return true;
	        }
	    }
	    return false;
	}
}
