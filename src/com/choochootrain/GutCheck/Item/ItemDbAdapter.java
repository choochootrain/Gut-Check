package com.choochootrain.GutCheck.Item;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemDbAdapter {

	private Context context;
	private ItemOpenHelper helper;
	private SQLiteDatabase db;
	private static SimpleDateFormat dateFormat;
	
    public ItemDbAdapter(Context context) {
    	this.context = context;
    	dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public ItemDbAdapter open() throws SQLException {
    	helper = new ItemOpenHelper(context);
    	db = helper.getWritableDatabase();
    	return this;
    }
    
    public void close() {
    	db.close();
    }
    
    public long createItem(Date dt, boolean result, boolean pending) {
    	ContentValues values = new ContentValues();
    	values.put("time", dateFormat.format(dt));
    	values.put("result", result ? 1 : 0);
    	values.put("pending", pending ? 1 : 0);
    	return db.insert(ItemOpenHelper.ITEM_TABLE_NAME, null, values);
    }
    
    public Cursor all() {
    	return db.query(ItemOpenHelper.ITEM_TABLE_NAME, 
    			ItemOpenHelper.COLUMNS, null, null, null, null, null);
    }
    
    public Cursor pending() throws SQLException {
    	Cursor results = db.query(true, ItemOpenHelper.ITEM_TABLE_NAME,
    			ItemOpenHelper.COLUMNS, ItemOpenHelper.COLUMNS[3] + "=0",
    			null, null, null, null, null);
    	if(results != null)
    		results.moveToFirst();
    	return results;
    }
    
    public Cursor get(long id) throws SQLException {
    	Cursor results = db.query(true, ItemOpenHelper.ITEM_TABLE_NAME,
    			ItemOpenHelper.COLUMNS, ItemOpenHelper.COLUMNS[0] + " " + id,
    			null, null, null, null, null);
    	if(results != null)
    		results.moveToFirst();
    	return results;
    }
}