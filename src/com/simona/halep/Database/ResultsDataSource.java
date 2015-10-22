package com.simona.halep.Database;

import java.util.ArrayList;
import java.util.List;

import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Database.Entities.Result;
import com.simona.halep.Pages.Ranks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ResultsDataSource {

		// Database fields
		  private SQLiteDatabase database;
		  private MySQLiteHelper dbHelper;
		  
		  public static final String TABLE = "results";
		  public static final String COLUMN_ID = "_id";
		  public static final String COLUMN_DATE = "date";
		  public static final String COLUMN_TOURNAMENT = "tournament";
		  public static final String COLUMN_ROUND = "round";
		  public static final String COLUMN_RESULT = "result";
		  public static final String COLUMN_OPPONENT = "opponent";
		  public static final String COLUMN_RANK = "rank";
		  public static final String COLUMN_SCORES = "scores";
		  private static final String DATABASE_NAME = "results.db";
		  
		  private static final String DATABASE_CREATE = "create table "
			      + TABLE + "(" + COLUMN_ID
			      + " integer primary key autoincrement, " + COLUMN_DATE
			      + " text not null, " + COLUMN_TOURNAMENT
			      + " text not null, " + COLUMN_ROUND
			      + " text not null, " + COLUMN_RESULT
			      + " text not null, " + COLUMN_OPPONENT
			      + " text not null, " + COLUMN_RANK
			      + " text not null, " + COLUMN_SCORES
		  		  + " text not null);";
		  
		  private String[] allColumns = { COLUMN_ID, COLUMN_DATE, COLUMN_TOURNAMENT, COLUMN_ROUND, COLUMN_RESULT, COLUMN_OPPONENT, COLUMN_RANK, COLUMN_SCORES };

	
		  public ResultsDataSource(Context context) {
			  dbHelper = new MySQLiteHelper(context, DATABASE_NAME, DATABASE_CREATE, TABLE);
		  }

		  public void open() throws SQLException {
			  database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
			  dbHelper.close();
		  }
		  
		  public Result createResult(String date, String tournament, String round, String result, String opponent, String rank, String score) {
			  ContentValues values = new ContentValues();
			  values.put(COLUMN_DATE, date);
			  values.put(COLUMN_TOURNAMENT, tournament);
			  values.put(COLUMN_ROUND, round);
			  values.put(COLUMN_RESULT, result);
			  values.put(COLUMN_OPPONENT, opponent);
			  values.put(COLUMN_RANK, rank);
			  values.put(COLUMN_SCORES, score);
			  long insertId = database.insert(TABLE, null, values);
			  Cursor cursor = database.query(TABLE, allColumns, COLUMN_ID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  Result newResult = cursorToResult(cursor);
			  cursor.close();
			  return newResult;
		  }
		  
		  public List<Result> getAllResults() {
			    List<Result> results = new ArrayList<Result>();

			    Cursor cursor = database.query(TABLE, allColumns, null, null, null, null, null);

			    cursor.moveToFirst();
			    while (!cursor.isAfterLast()) {
			      Result result = cursorToResult(cursor);
			      results.add(result);
			      cursor.moveToNext();
			    }
			    // make sure to close the cursor
			    cursor.close();
			    return results;
		  }
		  
		  public void deleteResult(Result result) {
			  long id = result.getId();
			  database.delete(TABLE, COLUMN_ID + " = " + id, null);
		  }
		  
		  private Result cursorToResult(Cursor cursor) {
			  Result result = new Result();
			  result.setId(cursor.getLong(0));
			  result.setDate(cursor.getString(1));
			  result.setTournament(cursor.getString(2));
			  result.setRound(cursor.getString(3));
			  result.setResult(cursor.getString(4));
			  result.setOpponent(cursor.getString(5));
			  result.setRank(cursor.getString(6));
			  result.setScore(cursor.getString(7));
			  return result;
		  }
}
