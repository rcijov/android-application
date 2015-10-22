package com.simona.halep.Database;

import java.util.ArrayList;
import java.util.List;

import com.simona.halep.Database.Entities.Stats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StatsDataSource {

		// Database fields
		  private SQLiteDatabase database;
		  private MySQLiteHelper dbHelper;
		  
		  public static final String TABLE = "stats";
		  public static final String COLUMN_ID = "_id";
		  public static final String COLUMN_STAT = "stat";
		  public static final String COLUMN_STAT_NR = "statNr";
		  private static final String DATABASE_NAME = "stats.db";
		  
		  private static final String DATABASE_CREATE = "create table "
			      + TABLE + "(" + COLUMN_ID
			      + " integer primary key autoincrement, " + COLUMN_STAT
			      + " text not null, " + COLUMN_STAT_NR
		  		  + " text not null);";
		  
		  private String[] allColumns = { COLUMN_ID, COLUMN_STAT, COLUMN_STAT_NR };

	
		  public StatsDataSource(Context context) {
			  dbHelper = new MySQLiteHelper(context, DATABASE_NAME, DATABASE_CREATE, TABLE);
		  }

		  public void open() throws SQLException {
			  database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
			  dbHelper.close();
		  }
		  
		  public Stats createStat(String stat, String statNr) {
			  ContentValues values = new ContentValues();
			  values.put(COLUMN_STAT, stat);
			  values.put(COLUMN_STAT_NR, statNr);
			  long insertId = database.insert(TABLE, null, values);
			  Cursor cursor = database.query(TABLE, allColumns, COLUMN_ID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  Stats newStat = cursorToStats(cursor);
			  cursor.close();
			  return newStat;
		  }
		  
		  public List<Stats> getAllStats() {
			    List<Stats> stats = new ArrayList<Stats>();

			    Cursor cursor = database.query(TABLE, allColumns, null, null, null, null, null);

			    cursor.moveToFirst();
			    while (!cursor.isAfterLast()) {
			      Stats stat = cursorToStats(cursor);
			      stats.add(stat);
			      cursor.moveToNext();
			    }
			    // make sure to close the cursor
			    cursor.close();
			    return stats;
		  }
		  
		  public void deleteStat(Stats stat) {
			  long id = stat.getId();
			  database.delete(TABLE, COLUMN_ID + " = " + id, null);
		  }
		  
		  private Stats cursorToStats(Cursor cursor) {
			  Stats stat = new Stats();
			  stat.setId(cursor.getLong(0));
			  stat.setStat(cursor.getString(1));
			  stat.setNrStat(cursor.getString(2));
			  return stat;
		  }
}
