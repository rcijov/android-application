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
		  private DBHelper crHelper;

		  public ResultsDataSource(MySQLiteHelper dbHelper) {
			  this.dbHelper = dbHelper;
			  this.crHelper = DBHelper.getInstance();
		  }

		  public void open() throws SQLException {
			  database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
			  dbHelper.close();
		  }
		  
		  public Result createResult(String date, String tournament, String round, String result, String opponent, String rank, String score) {
			  ContentValues values = new ContentValues();
			  values.put(crHelper.COLUMN_DATE, date);
			  values.put(crHelper.COLUMN_TOURNAMENT, tournament);
			  values.put(crHelper.COLUMN_ROUND, round);
			  values.put(crHelper.COLUMN_RESULT, result);
			  values.put(crHelper.COLUMN_OPPONENT, opponent);
			  values.put(crHelper.COLUMN_RANK, rank);
			  values.put(crHelper.COLUMN_SCORES, score);
			  long insertId = database.insert(crHelper.RESULTS, null, values);
			  Cursor cursor = database.query(crHelper.RESULTS, crHelper.resultsColumns, crHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  Result newResult = cursorToResult(cursor);
			  cursor.close();
			  return newResult;
		  }
		  
		  public List<Result> getAllResults() {
			    List<Result> results = new ArrayList<Result>();

			    try
			    {
			    	Cursor cursor = database.query(crHelper.RESULTS, crHelper.resultsColumns, null, null, null, null, null);

				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				      Result result = cursorToResult(cursor);
				      results.add(result);
				      cursor.moveToNext();
				    }
				    // make sure to close the cursor
				    cursor.close();
			    }
			    catch(Exception e)
			    {
			    	database.execSQL(crHelper.DATABASE_RESULTS);
			    }
			    
			    return results;
		  }
		  
		  public void deleteResult(Result result) {
			  long id = result.getId();
			  database.delete(crHelper.RESULTS, crHelper.COLUMN_ID + " = " + id, null);
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
