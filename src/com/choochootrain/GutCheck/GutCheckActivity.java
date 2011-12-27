package com.choochootrain.GutCheck;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.choochootrain.GutCheck.Item.Item;
import com.choochootrain.GutCheck.Item.ItemAdapter;

public class GutCheckActivity extends Activity {
	
	private ArrayList<Item> mItems = new ArrayList<Item>();;
	private ItemAdapter adapter;
	private ListView pendingItems;
	private TextView pendingLabel;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //get views
        pendingItems = (ListView)findViewById(R.id.pending_items);
        pendingLabel = (TextView)findViewById(R.id.pending_label);
        
        //start background service
        startService(new Intent(GutCheckService.class.getName()));
        
        //get pending item data
        getPendingItems();
        
        //set appropriate label
        setLabel();
        
        adapter = new ItemAdapter(this, R.layout.row, mItems);
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
            startActivity(new Intent(this,AboutActivity.class));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

	private void setLabel() {
        if(mItems.size() != 0)
        	pendingLabel.setText(R.string.pending_items);
        else
        	pendingLabel.setText(R.string.no_pending_items);
	}

	private void getPendingItems() {
        mItems.add(new Item(1, new Date()));
        mItems.add(new Item(2, new Date()));
        mItems.add(new Item(3, new Date()));
        mItems.add(new Item(4, new Date()));
        mItems.add(new Item(5, new Date()));
        mItems.add(new Item(6, new Date()));
        mItems.add(new Item(7, new Date()));
        mItems.add(new Item(8, new Date()));
        mItems.add(new Item(9, new Date()));
        mItems.add(new Item(10, new Date()));
        mItems.add(new Item(11, new Date()));
        mItems.add(new Item(12, new Date()));
        mItems.add(new Item(13, new Date()));
        mItems.add(new Item(14, new Date()));
        mItems.add(new Item(15, new Date()));
        mItems.add(new Item(16, new Date()));
        mItems.add(new Item(17, new Date()));
        mItems.add(new Item(18, new Date()));
        mItems.add(new Item(19, new Date()));
        mItems.add(new Item(20, new Date()));
	}

	public void removeItem(Item o) {
		mItems.remove(o);
		adapter.notifyDataSetChanged();
		setLabel();
	}
}