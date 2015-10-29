package com.simona.halep.Database;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	  private static final int DATABASE_VERSION = 1;
	  private List<String> DATABASE_CREATE;

	   public MySQLiteHelper(Context context, String databaseName, List<String> databaseCreate) {
	     super(context, databaseName, null, DATABASE_VERSION);
	     this.DATABASE_CREATE = databaseCreate;
	   }

	   @Override
	   public void onCreate(SQLiteDatabase database) {
			try
			{
				for (String data : DATABASE_CREATE) {
					database.execSQL(data);
				}
			}
			catch(Exception e)
			{
				Log.w(MySQLiteHelper.class.getName(), "Cannot create Database");
			}
	  	}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			for (String data : DATABASE_CREATE) {
				db.execSQL("DROP TABLE IF EXISTS " + data);
			}
			onCreate(db);
		}
}
