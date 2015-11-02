package com.simona.halep.Database;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_DATE = "date";
	  public static final String COLUMN_TITLE = "title";
	  public static final String COLUMN_BODY = "body";
	  public static final String NEWS = "news";
	  
	  public static final String RANK = "rank";
	  public static final String COLUMN_TOURNAMENT = "tournament";
	  public static final String COLUMN_ROUND = "round";
	  public static final String COLUMN_POINTS = "points";
	  
	  public static final String RESULTS = "results";
	  public static final String COLUMN_RESULT = "result";
	  public static final String COLUMN_OPPONENT = "opponent";
	  public static final String COLUMN_RANK = "rank";
	  public static final String COLUMN_SCORES = "scores";
	  
	  public static final String STATS = "stats";
	  public static final String COLUMN_STAT = "stat";
	  public static final String COLUMN_STAT_NR_YTD = "statNrYtd";
	  public static final String COLUMN_STAT_NR_CAR = "statNrCar";
	  
	  
	  public static final String DATABASE_NAME = "halep.db";
	  
	  public static List<String> listDatabases;
	  
	  public static String DATABASE_RANK = "create table "
		      + RANK + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_DATE
		      + " text not null, " + COLUMN_TOURNAMENT
		      + " text not null, " + COLUMN_ROUND
		      + " text not null, " + COLUMN_POINTS
	  		  + " text not null);";
			  
	  public String[] rankColumns = { COLUMN_ID, COLUMN_DATE, COLUMN_TOURNAMENT, COLUMN_ROUND, COLUMN_POINTS };
	  
	  public static String DATABASE_NEWS = "create table "
		      + NEWS + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_DATE
		      + " text not null, " + COLUMN_TITLE
		      + " text not null, " + COLUMN_BODY
	  		  + " text not null);";
	  
	  public String[] newsColumns = { COLUMN_ID, COLUMN_DATE, COLUMN_TITLE, COLUMN_BODY };
	  		  
	  public static String DATABASE_RESULTS = "create table "
		      + RESULTS + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_DATE
		      + " text not null, " + COLUMN_TOURNAMENT
		      + " text not null, " + COLUMN_ROUND
		      + " text not null, " + COLUMN_RESULT
		      + " text not null, " + COLUMN_OPPONENT
		      + " text not null, " + COLUMN_RANK
		      + " text not null, " + COLUMN_SCORES
	  		  + " text not null);";
	  		  
	  public String[] resultsColumns = { COLUMN_ID, COLUMN_DATE, COLUMN_TOURNAMENT, COLUMN_ROUND, COLUMN_RESULT, COLUMN_OPPONENT, COLUMN_RANK, COLUMN_SCORES };
	  
	  public static String DATABASE_STATS = "create table "
		      + STATS + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_STAT
		      + " text not null, " + COLUMN_STAT_NR_YTD
		      + " text not null, " + COLUMN_STAT_NR_CAR
	  		  + " text not null);";
	  
	  public String[] statsColumns = { COLUMN_ID, COLUMN_STAT, COLUMN_STAT_NR_YTD, COLUMN_STAT_NR_CAR};
	  
	  private static DBHelper instance = null;
	  
	  public static DBHelper getInstance()
	  {
		  if(instance == null)
		  {
			  instance = new DBHelper();
			  instance.listDatabases = new ArrayList<String>(); 
			  instance.listDatabases.add(DATABASE_RANK);
			  instance.listDatabases.add(DATABASE_NEWS);
			  instance.listDatabases.add(DATABASE_RESULTS);
			  instance.listDatabases.add(DATABASE_STATS);
		  }
		  
		  return instance;
	  }
}
