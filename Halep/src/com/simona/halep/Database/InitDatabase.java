package com.simona.halep.Database;

import java.util.ArrayList;
import java.util.List;

import com.simona.halep.Api.HelperApi;
import com.simona.halep.Api.NewsApi;
import com.simona.halep.Api.RanksApi;
import com.simona.halep.Api.ResultsApi;
import com.simona.halep.Api.StatsApi;
import com.simona.halep.Api.StrategyApi;
import com.simona.halep.Database.Entities.News;
import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;
import com.simona.halep.Pages.Home;
import com.simona.halep.Pages.Ranks;

import android.app.Activity;
import android.app.Fragment;

public class InitDatabase {

	private static InitDatabase instance = null;
	private static StrategyDataSource stratData = null;
	private static StrategyApi stratApi = null;
	
    public static InitDatabase getInstance(Activity activity) {
      if(instance == null) {
         instance = new InitDatabase();
         
         instance.stratApi = stratApi.getInstance(activity);
         instance.stratData = stratData.getInstance(activity);
      }
      return instance;
    }
	
	private void initNews()
	{
		if(stratData.getAllNews().size() == 0)
		{
			for(com.simona.halep.Database.Entities.News news : stratApi.getNews())
			{
				stratData.createNews(news);
			}
		}
	}
	
	public List<News> getNews()
	{
		initNews();
		List<News> newsList = stratData.getAllNews();
		return newsList;
	}
	
	private void initRanks()
	{
		
		if(stratData.getAllRanks().size() == 0)
		{
			for(Rank rank : stratApi.getRanks())
			{
				stratData.createRank(rank);
			}
		}
	}
	
	public List<Rank> getRanks()
	{
		initRanks();
		List<Rank> ranksList = stratData.getAllRanks();
		return ranksList;
	}
	
	private void initResults()
	{
		if(stratData.getAllResults().size() == 0)
		{
			
			for(Result result : stratApi.getResults())
			{	
				stratData.createResult(result);
			}
		}
	}
	
	public List<Result> getResults()
	{
		initResults();
		List<Result> resultsList = stratData.getAllResults();
		return resultsList;
	}
	
	private void initStats()
	{
		if(stratData.getAllStats().size() == 0)
		{
			for(Stats stat : stratApi.getStats())
			{
				stratData.createStats(stat);
			}
		}
	}
	
	public List<Stats> getStats()
	{
		initStats();
		List<Stats> statsList = stratData.getAllStats();
		return statsList;
	}
	
}
