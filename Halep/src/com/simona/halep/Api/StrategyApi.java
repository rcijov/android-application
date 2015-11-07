package com.simona.halep.Api;

import java.util.ArrayList;

import android.app.Activity;

import com.simona.halep.Database.InitDatabase;
import com.simona.halep.Database.Entities.News;
import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;
import com.simona.halep.Pages.Ranks;

public class StrategyApi {

	private static HelperApi helpApi = null;
	private static StatsApi statsApi = null;
	private static ResultsApi resultsApi = null;
	private static RanksApi ranksApi = null;
	private static NewsApi newsApi = null;
	
	private static StrategyApi instance = null;
	
	public static StrategyApi getInstance(Activity activity) {
		
		if(instance == null) {
			instance = new StrategyApi();
			instance.helpApi = helpApi.getInstance(activity);
			instance.statsApi = statsApi.getInstance();
			instance.resultsApi = resultsApi.getInstance();
			instance.newsApi = newsApi.getInstance();
			instance.ranksApi = ranksApi.getInstance();
		}
		return instance;
	}
	
	public ArrayList<News> getNews()
	{
		return newsApi.getNews();
	}
	
	public ArrayList<Rank> getRanks()
	{
		return ranksApi.getRanks();
	}
	
	public ArrayList<Result> getResults()
	{
		return resultsApi.getResults();
	}
	
	public ArrayList<Stats> getStats()
	{
		return statsApi.getStats();
	}
}
