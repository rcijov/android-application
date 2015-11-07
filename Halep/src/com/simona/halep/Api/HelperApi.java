package com.simona.halep.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.StrictMode;

public class HelperApi {

	private static HelperApi instance = null;
	private static Activity activity;
	
	public static HelperApi getInstance(Activity act)
	{
		if(instance == null)
		{
			// Avoid android.os.NetworkOnMainThreadException
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			activity = act;
			
			instance = new HelperApi();
		}
		
		return instance;
	}
	
	public static Activity getActivity()
	{
		return activity;
	}
	
	public static HelperApi getInstance()
	{
		if(instance == null)
		{
			// Avoid android.os.NetworkOnMainThreadException
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			
			instance = new HelperApi();
		}
		
		return instance;
	}
	
	public static boolean isOnline() {

	    Runtime runtime = Runtime.getRuntime();
	    try {

	        Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
	        int     exitValue = ipProcess.waitFor();
	        return (exitValue == 0);

	    } catch (IOException e)          { e.printStackTrace(); } 
	      catch (InterruptedException e) { e.printStackTrace(); }

	    return false;
	}
	
	public static String getJSON(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	}
	
}
