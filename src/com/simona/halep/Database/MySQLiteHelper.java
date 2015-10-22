package com.simona.halep.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	  private static final int DATABASE_VERSION = 1;
	  private String DATABASE_CREATE;
	  private String TABLE;

	  public MySQLiteHelper(Context context, String databaseName, String databaseCreate, String table) {
	    super(context, databaseName, null, DATABASE_VERSION);
	    this.DATABASE_CREATE = databaseCreate;
	    this.TABLE = table;
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }
	  
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE);
	    onCreate(db);
	  }
}
