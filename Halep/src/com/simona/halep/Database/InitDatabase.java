package com.simona.halep.Database;

import java.util.ArrayList;
import java.util.List;

import com.simona.halep.Api.NewsApi;
import com.simona.halep.Api.RanksApi;
import com.simona.halep.Api.ResultsApi;
import com.simona.halep.Api.StatsApi;
import com.simona.halep.Database.Entities.News;
import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;
import com.simona.halep.Pages.Home;
import com.simona.halep.Pages.Ranks;

import android.app.Activity;
import android.app.Fragment;

public class InitDatabase {

	private static NewsDataSource newsDataSource;
	private static RanksDataSource ranksDataSource;
	private static ResultsDataSource resultsDataSource;
	private static StatsDataSource statsDataSource;
	private static MySQLiteHelper dbHelper;
	private static DBHelper helpDatabase;
	private static InitDatabase instance = null;
	
	private static StatsApi statsApi = null;
	private static ResultsApi resultsApi = null;
	private static RanksApi ranksApi = null;
	private static NewsApi newsApi = null;
	
    public static InitDatabase getInstance(Activity activity) {
      if(instance == null) {
         instance = new InitDatabase();
         
         instance.statsApi = statsApi.getInstance();
         instance.resultsApi = resultsApi.getInstance();
         instance.ranksApi = ranksApi.getInstance();
         instance.newsApi = newsApi.getInstance();
         
         instance.helpDatabase = DBHelper.getInstance(); 
         instance.dbHelper = new MySQLiteHelper(activity, helpDatabase.DATABASE_NAME, helpDatabase.listDatabases);
			
         instance.statsDataSource = new StatsDataSource(dbHelper);
         instance.newsDataSource = new NewsDataSource(dbHelper);
         instance.ranksDataSource = new RanksDataSource(dbHelper);
         instance.resultsDataSource = new ResultsDataSource(dbHelper);
      }
      return instance;
    }
	
	private void initNews()
	{
		if(newsDataSource.getAllNews().size() == 0)
		{
			for(com.simona.halep.Database.Entities.News news : newsApi.getNews())
			{
				newsDataSource.createNews(news.getDate(), news.getTitle(), news.getBody());
			}
		}
	}
	
	public List<News> getNews()
	{
		newsDataSource.open();
		initNews();
		List<News> newsList = newsDataSource.getAllNews();
		newsDataSource.close();
		return newsList;
	}
	
	private void initRanks()
	{
		
		if(ranksDataSource.getAllRanks().size() == 0)
		{
			for(Rank rank : ranksApi.getRanks())
			{
				ranksDataSource.createRank(rank.getDate(), rank.getTournament(), rank.getRound(), rank.getPoints());
			}
		}
	}
	
	public List<Rank> getRanks()
	{
		ranksDataSource.open();
		initRanks();
		List<Rank> ranksList = ranksDataSource.getAllRanks();
		ranksDataSource.close();
		return ranksList;
	}
	
	private void initResults()
	{
		if(resultsDataSource.getAllResults().size() == 0)
		{
			
			for(Result result : resultsApi.getResults())
			{	
				resultsDataSource.createResult(result.getDate(), result.getTournament(), result.getRound(), result.getResult(), result.getOpponent()
			    		   , result.getRank(), result.getScore());
			}
		}
	}
	
	public List<Result> getResults()
	{
		resultsDataSource.open();
		initResults();
		List<Result> resultsList = resultsDataSource.getAllResults();
		resultsDataSource.close();
		return resultsList;
	}
	
	private void initStats()
	{
		if(statsDataSource.getAllStats().size() == 0)
		{
			for(Stats stat : statsApi.getStats())
			{
				statsDataSource.createStat(stat.getStat(),stat.getNrStatYtd(),stat.getNrStatCar());
			}
		}
	}
	
	public List<Stats> getStats()
	{
		statsDataSource.open();
		initStats();
		List<Stats> statsList = statsDataSource.getAllStats();
		statsDataSource.close();
		return statsList;
	}
	
}
