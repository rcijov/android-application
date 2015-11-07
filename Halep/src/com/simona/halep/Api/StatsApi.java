package com.simona.halep.Api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.StrictMode;

import com.simona.halep.Database.Entities.Stats;

public class StatsApi {

	private static HelperApi helpApi = null;
	private static StatsApi instance = null;
	private static Activity activity = null;
	
	public static StatsApi getInstance()
	{
		if(instance == null)
		{
			instance = new StatsApi();
			helpApi = helpApi.getInstance();
			activity = helpApi.getActivity();
		}
		
		return instance;
	}
	
	
	public static ArrayList<Stats> getStats()
	{	
		if(helpApi.isOnline())
		{
			return online();
		}
		else
		{
			return offline();
		}
	}
	
	private static ArrayList<Stats> online()
	{
		String urlString = "https://www.kimonolabs.com/api/c6es6nss?apikey=EuXTaIb1UyOnvRL4HebQkXbTy1rfN6XY";
		ArrayList<Stats> stats = new ArrayList<Stats>();
		
		try {

			JSONObject jObject = new JSONObject(helpApi.getJSON(urlString));
			JSONObject results = (JSONObject) jObject.get("results");
			JSONArray array = (JSONArray) results.get("collection1");
			
			int size = array.length();
			
			for(int i = 0; i < size; i++)
			{
				JSONObject prop1 = (JSONObject) array.get(i);
				Stats stat = new Stats();
		    	stat.setStat(prop1.get("property1").toString());
		    	stat.setNrStatCar(prop1.get("property2").toString());
		    	stat.setNrStatYtd(prop1.get("property3").toString());
		    	stats.add(stat);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stats;
	}
	
	private static ArrayList<Stats> offline()
	{
		ArrayList<Stats> stats = new ArrayList<Stats>();
		String csvFile = "overviewStats.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new InputStreamReader(activity.getAssets().open(csvFile)));
			while ((line = br.readLine()) != null) {
				String[] result = line.split(cvsSplitBy);
				Stats stat = new Stats();
				stat.setStat(result[0].replace("\"",""));
				stat.setNrStatCar(result[1].replace("\"",""));
				stat.setNrStatYtd(result[2].replace("\"",""));
				stats.add(stat);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stats;
	}
	
}
