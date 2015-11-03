package com.simona.halep.Api;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.simona.halep.Database.Entities.News;
import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;

public class NewsApi {

	private static NewsApi instance = null;
	private static HelperApi helpApi;
	
	public static NewsApi getInstance()
	{
		if(instance == null)
		{
			instance = new NewsApi();
			helpApi = helpApi.getInstance();
		}
		return instance;
	}
	
	public static ArrayList<News> getNews()
	{	
		String urlString = "https://www.kimonolabs.com/api/3mcrwvk8?apikey=EuXTaIb1UyOnvRL4HebQkXbTy1rfN6XY";
		ArrayList<News> resultArray = new ArrayList<News>();
		
		try {

			JSONObject jObject = new JSONObject(helpApi.getJSON(urlString));
			JSONObject results = (JSONObject) jObject.get("results");
			JSONArray array = (JSONArray) results.get("collection1");
			
			int size = array.length();
			
			for(int i = 0; i < size; i++)
			{
				JSONObject prop1 = (JSONObject) array.get(i);
				News news = new News();
		    	news.setTitle(prop1.get("property1").toString());
		    	news.setDate(prop1.get("property2").toString());
		    	news.setBody(prop1.get("property3").toString());
		    	resultArray.add(news);
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
