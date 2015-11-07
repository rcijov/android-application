package com.simona.halep.Database;

import java.util.ArrayList;
import java.util.List;

import com.simona.halep.Database.Entities.News;
import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Database.Entities.Stats;

import android.app.Activity;

public class StrategyDataSource {

	private static NewsDataSource newsDataSource;
	private static RanksDataSource ranksDataSource;
	private static ResultsDataSource resultsDataSource;
	private static StatsDataSource statsDataSource;
	private static MySQLiteHelper dbHelper;
	private static DBHelper helpDatabase;
	
	private static StrategyDataSource instance = null;
	
	public static StrategyDataSource getInstance(Activity activity)
	{
		if(instance == null)
		{
			 instance = new StrategyDataSource();
			 instance.helpDatabase = DBHelper.getInstance(); 
	         instance.dbHelper = new MySQLiteHelper(activity, helpDatabase.DATABASE_NAME, helpDatabase.listDatabases);
				
	         instance.statsDataSource = new StatsDataSource(dbHelper);
	         instance.newsDataSource = new NewsDataSource(dbHelper);
	         instance.ranksDataSource = new RanksDataSource(dbHelper);
	         instance.resultsDataSource = new ResultsDataSource(dbHelper);
			
		}
		
		return instance;
	}
	
	public List<News> getAllNews()
	{
		newsDataSource.open();
		List<News> newsList = newsDataSource.getAllNews();
		newsDataSource.close();
		return newsList;
	}
	
	
	public News createNews(News news)
	{
		newsDataSource.open();
		News newNews = newsDataSource.createNews(news.getDate(), news.getTitle(), news.getBody());
		newsDataSource.close();
		return newNews;
	}
	
	public List<Rank> getAllRanks()
	{
		ranksDataSource.open();
		List<Rank> ranksList = ranksDataSource.getAllRanks();
		ranksDataSource.close();
		return ranksList;
	}
	
	public Rank createRank(Rank rank)
	{
		ranksDataSource.open();
		Rank newRank = ranksDataSource.createRank(rank.getDate(), rank.getTournament(), rank.getRound(), rank.getPoints());
		ranksDataSource.close();
		return newRank;
	}
	
	public List<Result> getAllResults()
	{
		resultsDataSource.open();
		List<Result> resultList = resultsDataSource.getAllResults();
		resultsDataSource.close();
		return resultList;
	}
	
	public Result createResult(Result result)
	{
		resultsDataSource.open();
		Result newResult = resultsDataSource.createResult(result.getDate(), result.getTournament(), result.getRound(), result.getResult(), result.getOpponent()
	    		   , result.getRank(), result.getScore());
		resultsDataSource.close();
		return newResult;
	}
	
	public List<Stats> getAllStats()
	{
		statsDataSource.open();
		List<Stats> statsList = statsDataSource.getAllStats();
		statsDataSource.close();
		return statsList;
	}
	
	public Stats createStats(Stats stat)
	{
		statsDataSource.open();
		Stats newStats = statsDataSource.createStat(stat.getStat(),stat.getNrStatYtd(),stat.getNrStatCar());
		statsDataSource.close();
		return newStats;
	}
}
