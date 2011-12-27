package com.choochootrain.GutCheck.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemOpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "GutCheckDatabase";
	private static final String TABLE_NAME = "ITEMS";
	private static final String ID_COL = "_id";
	private static final String TIME_COL = "time";
	private static final String RESULT_COL = "result";
	private static final String DATE_COL = "created_date";
	
	public ItemOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//CREATE TABLE ITEMS ( 
	//	_id INTEGER PRIMARY KEY AUTOINCREMENT,
	// 	time DATETIME,
	//	result INTEGER,
	//	created_date DATETIME DEFAULT CURRENT_VALUE)
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
					ID_COL 		+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
					TIME_COL 	+ " DATETIME," 							+
					RESULT_COL 	+ " INTEGER," 							+
					DATE_COL 	+ " DATETIME DEFAULT CURRENT_VALUE" 	+
					");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
