package com.choochootrain.GutCheck.Item;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import com.choochootrain.GutCheck.R;

public class AllItemsCursorAdapter extends SimpleCursorAdapter {

    public AllItemsCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = super.newView(context, cursor, parent);
        setBgColor(view, cursor);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        setBgColor(view, cursor);
    }

    private void setBgColor(View view, Cursor cursor) {
        RelativeLayout resultLayout = (RelativeLayout)view.findViewById(R.id.result_layout);
        int result = cursor.getInt(cursor.getColumnIndexOrThrow(ItemOpenHelper.COLUMNS[2]));
        resultLayout.setBackgroundColor(getBgColor(result));
    }
    
    private int getBgColor(int result) {
        if (result == -1)
            return R.color.button_yellow;
        else if (result == 1)
            return R.color.button_green;
        else
            return R.color.button_red;
    }
}
