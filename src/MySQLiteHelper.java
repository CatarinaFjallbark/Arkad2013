package com.tlth.arkad13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "favvo.db";
	private static final int DATABASE_VERSION = 1;
	
	 public static final String TABLE_FAVVOS = "favvos";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_FAVVO = "favvo";
	
	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_FAVVOS + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_FAVVO
	      + " text not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVVOS);
	    onCreate(db);
	  }
}
