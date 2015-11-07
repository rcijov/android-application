package com.simona.halep.Api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;

public class RanksApi {

	private static RanksApi instance = null;
	private static HelperApi helpApi;
	private static Activity activity = null;
	
	public static RanksApi getInstance()
	{
		if(instance == null)
		{
			instance = new RanksApi();
			helpApi = helpApi.getInstance();
			activity = helpApi.getActivity();
		}
		return instance;
	}
	
	public static ArrayList<Rank> getRanks()
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
	
	private static ArrayList<Rank> online()
	{
		String urlString = "https://www.kimonolabs.com/api/9cvgr58m?apikey=EuXTaIb1UyOnvRL4HebQkXbTy1rfN6XY";
		ArrayList<Rank> resultArray = new ArrayList<Rank>();
		
		try {

			JSONObject jObject = new JSONObject(helpApi.getJSON(urlString));
			JSONObject results = (JSONObject) jObject.get("results");
			JSONArray array = (JSONArray) results.get("collection1");
			
			int size = array.length();
			
			for(int i = 0; i < size; i++)
			{
				JSONObject prop1 = (JSONObject) array.get(i);
				Rank rank = new Rank();
		    	rank.setDate(prop1.get("property1").toString());
		    	rank.setTournament(prop1.get("property2").toString());
		    	rank.setRound(prop1.get("property3").toString());
		    	rank.setPoints(prop1.get("property4").toString());
		    	resultArray.add(rank);
			}		
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultArray;
	}
	
	private static ArrayList<Rank> offline()
	{
		ArrayList<Rank> ranks = new ArrayList<Rank>();
		String csvFile = "overviewRanks.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new InputStreamReader(activity.getAssets().open(csvFile)));
			while ((line = br.readLine()) != null) {
				String[] result = line.split(cvsSplitBy);
				Rank rank = new Rank();
				rank.setDate(result[0].replace("\"",""));
				rank.setTournament(result[1].replace("\"",""));
				rank.setRound(result[2].replace("\"",""));
				rank.setPoints(result[3].replace("\"",""));
				ranks.add(rank);
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
		return ranks;
	}
}
