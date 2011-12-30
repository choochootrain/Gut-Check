package com.choochootrain.GutCheck.Item;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemOpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "GutCheckDatabase";
	public static final String ITEM_TABLE_NAME = "ITEMS";
	public static final String[] COLUMNS = {"_id", "time", "result", "pending", "created_date"};
	
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
		db.execSQL("CREATE TABLE " + ITEM_TABLE_NAME + " (" +
					COLUMNS[0]	+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
					COLUMNS[1]	+ " DATETIME," 							+
					COLUMNS[2] 	+ " INTEGER," 							+
					COLUMNS[3]	+ " INTEGER,"							+
					COLUMNS[4]	+ " DATETIME DEFAULT CURRENT_VALUE" 	+
					");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
