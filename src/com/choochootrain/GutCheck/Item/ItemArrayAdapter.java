package com.choochootrain.GutCheck.Item;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.choochootrain.GutCheck.GutCheckActivity;
import com.choochootrain.GutCheck.R;

public class ItemArrayAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> items;
    private Context context;
    private ItemDbAdapter dbAdapter;

    public ItemArrayAdapter(Context context, int textViewResourceId, ArrayList<Item> items, ItemDbAdapter adapter) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.context = context;
            dbAdapter = adapter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row, null);
            }
            final Item o = items.get(position);
            if (o != null) {
                    TextView time = (TextView) v.findViewById(R.id.time);
                    Button yes = (Button) v.findViewById(R.id.button_yes);
                    yes.getBackground().setColorFilter(context.getResources().getColor(R.color.button_green), PorterDuff.Mode.MULTIPLY);
                    yes.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							removeItem(o, true);
						}
                    });
                    Button no = (Button) v.findViewById(R.id.button_no);
                    no.getBackground().setColorFilter(context.getResources().getColor(R.color.button_red), PorterDuff.Mode.MULTIPLY);
                    no.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							removeItem(o, false);
						}
                    });
                    if (time != null)
                          time.setText(o.getId() + " " + o.getTime().toLocaleString());
            }
            return v;
    }

	protected void removeItem(Item o, boolean b) {
		GutCheckActivity activity = (GutCheckActivity)context;
		activity.removeItem(o);
		Toast.makeText(getContext(), "" + o.getId(), Toast.LENGTH_SHORT).show();
		dbAdapter.updateItem(o.getId(), b);
	}
}