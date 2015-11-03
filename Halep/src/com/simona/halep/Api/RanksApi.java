package com.simona.halep.Api;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;

public class RanksApi {

	private static RanksApi instance = null;
	private static HelperApi helpApi;
	
	public static RanksApi getInstance()
	{
		if(instance == null)
		{
			instance = new RanksApi();
			helpApi = helpApi.getInstance();
		}
		return instance;
	}
	
	public static ArrayList<Rank> getRanks()
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
	
}
