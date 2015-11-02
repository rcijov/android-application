package com.simona.halep.Api;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;

public class ResultsApi {

	private static ResultsApi instance = null;
	private static HelperApi helpApi;
	
	public static ResultsApi getInstance()
	{
		if(instance == null)
		{
			instance = new ResultsApi();
			helpApi = helpApi.getInstance();
		}
		return instance;
	}
	
	public static ArrayList<Result> getResults()
	{	
		String urlString = "https://www.kimonolabs.com/api/8l7zkyxm?apikey=EuXTaIb1UyOnvRL4HebQkXbTy1rfN6XY";
		ArrayList<Result> resultArray = new ArrayList<Result>();
		
		try {

			JSONObject jObject = new JSONObject(helpApi.getJSON(urlString));
			JSONObject results = (JSONObject) jObject.get("results");
			JSONArray array = (JSONArray) results.get("collection1");
			JSONArray arraySecond = (JSONArray) results.get("collection2");
			
			int size = array.length();
			
			for(int i = 0; i < size; i++)
			{
				JSONObject prop1 = (JSONObject) array.get(i);
				Result result = new Result();
		    	result.setRound(prop1.get("property1").toString());
		    	result.setResult(prop1.get("property2").toString());
		    	
		    	JSONObject prop3 =  (JSONObject) prop1.get("property3");
		    	result.setOpponent(prop3.getString("text").toString());
		    	
		    	result.setRank(prop1.get("property4").toString());
		    	result.setScore(prop1.get("property5").toString());
		    	
		    	JSONObject prop2 = (JSONObject) arraySecond.get(0);
		    	JSONObject prop6 =  (JSONObject) prop2.get("property6");
		    	result.setTournament(prop6.getString("text").toString());
		    	
		    	String[] date = (prop2.get("property7").toString()).split("//");
		    	result.setDate(date[2]);
		    	
		    	resultArray.add(result);
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
