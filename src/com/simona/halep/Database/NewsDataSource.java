package com.simona.halep.Database;

import java.util.ArrayList;
import java.util.List;

import com.simona.halep.Database.Entities.News;
import com.simona.halep.Database.Entities.Stats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NewsDataSource {

		// Database fields
		  private SQLiteDatabase database;
		  private MySQLiteHelper dbHelper;
		  
		  public static final String TABLE = "news";
		  public static final String COLUMN_ID = "_id";
		  public static final String COLUMN_DATE = "date";
		  public static final String COLUMN_TITLE = "title";
		  public static final String COLUMN_BODY = "body";
		  private static final String DATABASE_NAME = "news.db";
		  
		  private static final String DATABASE_CREATE = "create table "
			      + TABLE + "(" + COLUMN_ID
			      + " integer primary key autoincrement, " + COLUMN_DATE
			      + " text not null, " + COLUMN_TITLE
			      + " text not null, " + COLUMN_BODY
		  		  + " text not null);";
		  
		  private String[] allColumns = { COLUMN_ID, COLUMN_DATE, COLUMN_TITLE, COLUMN_BODY };

	
		  public NewsDataSource(Context context) {
			  dbHelper = new MySQLiteHelper(context, DATABASE_NAME, DATABASE_CREATE, TABLE);
		  }

		  public void open() throws SQLException {
			  database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
			  dbHelper.close();
		  }
		  
		  public News createNews(String date, String title, String body) {
			  ContentValues values = new ContentValues();
			  values.put(COLUMN_DATE, date);
			  values.put(COLUMN_TITLE, title);
			  values.put(COLUMN_BODY, body);
			  long insertId = database.insert(TABLE, null, values);
			  Cursor cursor = database.query(TABLE, allColumns, COLUMN_ID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  News news = cursorToNews(cursor);
			  cursor.close();
			  return news;
		  }
		  
		  public List<News> getAllNews() {
			    List<News> newsList = new ArrayList<News>();

			    Cursor cursor = database.query(TABLE, allColumns, null, null, null, null, null);

			    cursor.moveToFirst();
			    while (!cursor.isAfterLast()) {
			      News news = cursorToNews(cursor);
			      newsList.add(news);
			      cursor.moveToNext();
			    }
			    // make sure to close the cursor
			    cursor.close();
			    return newsList;
		  }
		  
		  public void deleteNews(News news) {
			  long id = news.getId();
			  database.delete(TABLE, COLUMN_ID + " = " + id, null);
		  }
		  
		  private News cursorToNews(Cursor cursor) {
			  News news = new News();
			  news.setId(cursor.getLong(0));
			  news.setDate(cursor.getString(1));
			  news.setTitle(cursor.getString(2));
			  news.setBody(cursor.getString(3));
			  return news;
		  }
}
