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

import com.simona.halep.Database.Entities.News;
import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;

public class NewsApi {

	private static NewsApi instance = null;
	private static HelperApi helpApi;
	private static Activity activity = null;
	
	public static NewsApi getInstance()
	{
		if(instance == null)
		{
			instance = new NewsApi();
			helpApi = helpApi.getInstance();
			activity = helpApi.getActivity();
		}
		return instance;
	}
	
	public static ArrayList<News> getNews()
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
	
	private static ArrayList<News> online()
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
	
	private static ArrayList<News> offline()
	{
		ArrayList<News> newsArray = new ArrayList<News>();
		String csvFile = "allNews.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new InputStreamReader(activity.getAssets().open(csvFile)));
			while ((line = br.readLine()) != null) {
				String[] result = line.split(cvsSplitBy);
				News news = new News();
				news.setTitle(result[0].replace("\"",""));
				news.setDate(result[1].replace("\"",""));
				news.setBody(result[2].replace("\"",""));
				newsArray.add(news);
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
		return newsArray;
	}
	
}
