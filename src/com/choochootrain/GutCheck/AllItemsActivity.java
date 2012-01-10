package com.choochootrain.GutCheck;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import com.choochootrain.GutCheck.Item.AllItemsCursorAdapter;
import com.choochootrain.GutCheck.Item.ItemDbAdapter;
import com.choochootrain.GutCheck.Item.ItemOpenHelper;

public class AllItemsActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending);

        //get db
        ItemDbAdapter dbAdapter = new ItemDbAdapter(this);
        dbAdapter.open();

        //get cursor
        Cursor cursor = dbAdapter.all();
        startManagingCursor(cursor);

        //columns to bind
        String[] columns = new String[]{ItemOpenHelper.COLUMNS[1], ItemOpenHelper.COLUMNS[2]};

        //views to bind to
        int[] to = new int[]{R.id.time_entry, R.id.result_entry};

        //create adapter
        SimpleCursorAdapter mAdapter = new AllItemsCursorAdapter(this, R.layout.all_items_row, cursor, columns, to);
        this.setListAdapter(mAdapter);
    }
}
