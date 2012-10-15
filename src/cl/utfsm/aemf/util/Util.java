package cl.utfsm.aemf.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Util Class: Privides a set of numerous static utilities
 * 
 * @author sebastian
 * 
 */
public class Util {

	public static int getProcessId(String applicationId) {
		String line = "";

		try {
			Process process = Runtime.getRuntime().exec("ps");
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((line = br.readLine()) != null) {
				String s[] = line.split(" ");
				System.out.println(s);
				if(s[s.length-1].contains(applicationId)){
					return Integer.valueOf(s[4]);
				}
			}
		} catch (Exception e) {
		}

		return -1;
	}
}
