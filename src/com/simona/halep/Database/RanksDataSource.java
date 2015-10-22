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
		  
		  public static final String TABLE = "rank";
		  public static final String COLUMN_ID = "_id";
		  public static final String COLUMN_DATE = "date";
		  public static final String COLUMN_TOURNAMENT = "tournament";
		  public static final String COLUMN_ROUND = "round";
		  public static final String COLUMN_POINTS = "points";
		  private static final String DATABASE_NAME = "ranks.db";
		  
		  private static final String DATABASE_CREATE = "create table "
			      + TABLE + "(" + COLUMN_ID
			      + " integer primary key autoincrement, " + COLUMN_DATE
			      + " text not null, " + COLUMN_TOURNAMENT
			      + " text not null, " + COLUMN_ROUND
			      + " text not null, " + COLUMN_POINTS
		  		  + " text not null);";
		  
		  private String[] allColumns = { COLUMN_ID, COLUMN_DATE, COLUMN_TOURNAMENT, COLUMN_ROUND, COLUMN_POINTS };

	
		  public RanksDataSource(Context context) {
			  dbHelper = new MySQLiteHelper(context, DATABASE_NAME, DATABASE_CREATE, TABLE);
		  }

		  public void open() throws SQLException {
			  database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
			  dbHelper.close();
		  }
		  
		  public Rank createRank(String date, String tournament, String round, String points) {
			  ContentValues values = new ContentValues();
			  values.put(COLUMN_DATE, date);
			  values.put(COLUMN_TOURNAMENT, tournament);
			  values.put(COLUMN_ROUND, round);
			  values.put(COLUMN_POINTS, points);
			  long insertId = database.insert(TABLE, null, values);
			  Cursor cursor = database.query(TABLE, allColumns, COLUMN_ID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  Rank newRank = cursorToRanks(cursor);
			  cursor.close();
			  return newRank;
		  }
		  
		  public List<Rank> getAllRanks() {
			    List<Rank> ranks = new ArrayList<Rank>();

			    Cursor cursor = database.query(TABLE, allColumns, null, null, null, null, null);

			    cursor.moveToFirst();
			    while (!cursor.isAfterLast()) {
			      Rank rank = cursorToRanks(cursor);
			      ranks.add(rank);
			      cursor.moveToNext();
			    }
			    // make sure to close the cursor
			    cursor.close();
			    return ranks;
		  }
		  
		  public void deleteRank(Rank rank) {
			  long id = rank.getId();
			  database.delete(TABLE, COLUMN_ID + " = " + id, null);
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
