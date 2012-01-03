package com.choochootrain.GutCheck;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.choochootrain.GutCheck.Item.Item;
import com.choochootrain.GutCheck.Item.ItemArrayAdapter;
import com.choochootrain.GutCheck.Item.ItemDbAdapter;
import com.choochootrain.GutCheck.Item.ItemOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GutCheckActivity extends Activity {

    private ArrayList<Item> mItems;
    private ItemArrayAdapter adapter;
    private ListView pendingItems;
    private TextView pendingLabel;
    private RelativeLayout noItemsLayout;
    private ItemDbAdapter dbAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //get views
        pendingItems = (ListView) findViewById(R.id.pending_items);
        pendingLabel = (TextView) findViewById(R.id.pending_label);
        noItemsLayout = (RelativeLayout) findViewById(R.id.no_items_layout);

        //get db
        dbAdapter = new ItemDbAdapter(this);
        dbAdapter.open();

        //start background service
        startService(new Intent(GutCheckService.class.getName()));

        //get pending item data
        mItems = new ArrayList<Item>();
        getPendingItems();

        //set appropriate label
        setLabel();

        adapter = new ItemArrayAdapter(this, R.layout.row, mItems, dbAdapter);
        pendingItems.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.all_items:
                startActivity(new Intent(this, AllItemsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setLabel() {
        if (mItems.size() != 0) {
            noItemsLayout.setVisibility(View.GONE);
            pendingLabel.setVisibility(View.GONE);
        } else {
            noItemsLayout.setVisibility(View.VISIBLE);
            pendingLabel.setVisibility(View.VISIBLE);
        }
    }

    private void getPendingItems() {
        Cursor c = dbAdapter.pending();
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (!c.moveToFirst())
            return;

        do {
            String dateTime = c.getString(c.getColumnIndexOrThrow(ItemOpenHelper.COLUMNS[1]));
            Date date = new Date(0);
            long id = c.getLong(c.getColumnIndexOrThrow(ItemOpenHelper.COLUMNS[0]));

            try {
                date = iso8601Format.parse(dateTime);
            } catch (ParseException e) {
                Log.e("GutCheck", "date parse failed.");
            }

            mItems.add(new Item(id, date));

        } while (c.moveToNext());

        c.close();
    }

    public void removeItem(Item o) {
        mItems.remove(o);
        adapter.notifyDataSetChanged();
        setLabel();
    }
}