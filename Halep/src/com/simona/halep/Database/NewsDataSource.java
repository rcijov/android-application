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
		  private DBHelper crHelper;

		  public NewsDataSource(MySQLiteHelper dbHelper) {
			  this.dbHelper = dbHelper;
			  this.crHelper = DBHelper.getInstance();
		  }

		  public void open() throws SQLException {
			  database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
			  dbHelper.close();
		  }
		  
		  public News createNews(String date, String title, String body) {
			  ContentValues values = new ContentValues();
			  values.put(crHelper.COLUMN_DATE, date);
			  values.put(crHelper.COLUMN_TITLE, title);
			  values.put(crHelper.COLUMN_BODY, body);
			  long insertId = database.insert(crHelper.NEWS, null, values);
			  Cursor cursor = database.query(crHelper.NEWS, crHelper.newsColumns, crHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  News news = cursorToNews(cursor);
			  cursor.close();
			  return news;
		  }
		  
		  public List<News> getAllNews() {
			    List<News> newsList = new ArrayList<News>();

			    try
			    {
			    	Cursor cursor = database.query(crHelper.NEWS, crHelper.newsColumns, null, null, null, null, null);
			    	cursor.moveToFirst();
				    while (!cursor.isAfterLast()) {
				      News news = cursorToNews(cursor);
				      newsList.add(news);
				      cursor.moveToNext();
				    }
				    // make sure to close the cursor
				    cursor.close();
			    }
			    catch(Exception e)
			    {
			    	database.execSQL(crHelper.DATABASE_NEWS);
			    }
			    
			    return newsList;
		  }
		  
		  public void deleteNews(News news) {
			  long id = news.getId();
			  database.delete(crHelper.NEWS, crHelper.COLUMN_ID + " = " + id, null);
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
