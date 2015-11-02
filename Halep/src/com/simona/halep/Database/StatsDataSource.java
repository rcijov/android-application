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
		  private DBHelper crHelper;
		  
		  public StatsDataSource(MySQLiteHelper dbHelper) {
			  this.dbHelper = dbHelper;
			  this.crHelper = DBHelper.getInstance();
		  }

		  public void open() throws SQLException {
			  database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
			  dbHelper.close();
		  }
		  
		  public Stats createStat(String stat, String statNrYtd, String statNrCar) {
			  ContentValues values = new ContentValues();
			  values.put(crHelper.COLUMN_STAT, stat);
			  values.put(crHelper.COLUMN_STAT_NR_YTD, statNrYtd);
			  values.put(crHelper.COLUMN_STAT_NR_CAR, statNrCar);
			  long insertId = database.insert(crHelper.STATS, null, values);
			  Cursor cursor = database.query(crHelper.STATS, crHelper.statsColumns, crHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  Stats newStat = cursorToStats(cursor);
			  cursor.close();
			  return newStat;
		  }
		  
		  public List<Stats> getAllStats() {
			  
			    List<Stats> stats = new ArrayList<Stats>();

			    try
			    {
			    	Cursor cursor = database.query(crHelper.STATS, crHelper.statsColumns, null, null, null, null, null);

				    cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				      Stats stat = cursorToStats(cursor);
				      stats.add(stat);
				      cursor.moveToNext();
				    }
				    // make sure to close the cursor
				    cursor.close();
			    }
			    catch(Exception e)
			    {
			    	database.execSQL(crHelper.DATABASE_STATS);
			    }
			    
			    return stats;
		  }
		  
		  public void deleteStat(Stats stat) {
			  long id = stat.getId();
			  database.delete(crHelper.STATS, crHelper.COLUMN_ID + " = " + id, null);
		  }
		  
		  private Stats cursorToStats(Cursor cursor) {
			  Stats stat = new Stats();
			  stat.setId(cursor.getLong(0));
			  stat.setStat(cursor.getString(1));
			  stat.setNrStatYtd(cursor.getString(2));
			  stat.setNrStatCar(cursor.getString(3));
			  return stat;
		  }
}
