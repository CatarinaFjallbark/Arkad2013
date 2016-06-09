package com.tlth.arkad13;

import android.app.Application;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ApplicationClass extends Application {
	private Companies headLines;
	private Posts articles;
	private Events ev;
	private static Application app;

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_FAVVO };

	public void onCreate() {
		headLines = new Companies();
		app = this;
		dbHelper = new MySQLiteHelper(this.getApplicationContext());

		try {
			articles = new Posts();
			ev = new Events();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated catch block

	}

	public Companies getHead() {
		return headLines;
	}

	public Posts getArticles() {
		return articles;
	}

	public Events getEventList() {
		return ev;
	}

	public static Application getApp() {
		return app;
	}

	public int isFavvo(int id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_FAVVOS, allColumns,
				MySQLiteHelper.COLUMN_ID + " = " + id, null, null, null, null);
		return cursor.getInt(0);
	}

	public void setFavvo(int id, int value) {

		if(value == 0){
			database.delete(MySQLiteHelper.TABLE_FAVVOS, MySQLiteHelper.COLUMN_ID
					+ " = " + id, null);
		}else if(value == 1){
			database.insert(MySQLiteHelper.TABLE_FAVVOS, MySQLiteHelper.COLUMN_ID
					+ " = " + id, null);
		}else{
			if(isFavvo(id) == 1){
				database.insert(MySQLiteHelper.TABLE_FAVVOS, MySQLiteHelper.COLUMN_ID
						+ " = " + id, null);	
			}
		}
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

}
