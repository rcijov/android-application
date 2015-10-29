package com.simona.halep.Database;

import java.util.ArrayList;
import java.util.List;

import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.Pages.Ranks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RanksDataSource {

		// Database fields
		  private SQLiteDatabase database;
		  private MySQLiteHelper dbHelper;
		  private DBHelper crHelper;

		  public RanksDataSource(MySQLiteHelper dbHelper) {
			  this.dbHelper = dbHelper;
			  this.crHelper = DBHelper.getInstance();
		  }

		  public void open() throws SQLException {
			  database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
			  dbHelper.close();
		  }
		  
		  public Rank createRank(String date, String tournament, String round, String points) {
			  ContentValues values = new ContentValues();
			  values.put(crHelper.COLUMN_DATE, date);
			  values.put(crHelper.COLUMN_TOURNAMENT, tournament);
			  values.put(crHelper.COLUMN_ROUND, round);
			  values.put(crHelper.COLUMN_POINTS, points);
			  long insertId = database.insert(crHelper.RANK, null, values);
			  Cursor cursor = database.query(crHelper.RANK, crHelper.rankColumns, crHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  Rank newRank = cursorToRanks(cursor);
			  cursor.close();
			  return newRank;
		  }
		  
		  public List<Rank> getAllRanks() {
			  
			    List<Rank> ranks = new ArrayList<Rank>();

			    try
			    {
			    	Cursor cursor = database.query(crHelper.RANK, crHelper.rankColumns, null, null, null, null, null);

				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				      Rank rank = cursorToRanks(cursor);
				      ranks.add(rank);
				      cursor.moveToNext();
				    }
				    // make sure to close the cursor
				    cursor.close();
			    }
			    catch(Exception e)
			    {
			    	database.execSQL(crHelper.DATABASE_RANK);
			    }
			    
			    return ranks;
		  }
		  
		  public void deleteRank(Rank rank) {
			  long id = rank.getId();
			  database.delete(crHelper.RANK, crHelper.COLUMN_ID + " = " + id, null);
		  }
		  
		  private Rank cursorToRanks(Cursor cursor) {
			  Rank rank = new Rank();
			  rank.setId(cursor.getLong(0));
			  rank.setDate(cursor.getString(1));
			  rank.setTournament(cursor.getString(2));
			  rank.setRound(cursor.getString(3));
			  rank.setPoints(cursor.getString(4));
			  return rank;
		  }
}
