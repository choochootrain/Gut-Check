package com.choochootrain.GutCheck;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GutCheckActivity extends Activity {
	private Button yesButton;
	private Button noButton;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        yesButton = (Button)findViewById(R.id.button_yes);
        yesButton.getBackground().setColorFilter(0xFF00CC00, PorterDuff.Mode.MULTIPLY);
        yesButton.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		//record this as a productive hour
        		recordAction(true);
        	}
        });
        
        noButton = (Button)findViewById(R.id.button_no);
        noButton.getBackground().setColorFilter(0xFFCC0000, PorterDuff.Mode.MULTIPLY);
        noButton.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		//record this as an unproductive hour
        		recordAction(false);
        	}
        });
        
        //start background service
        startService(new Intent(GutCheckService.class.getName()));
    }
    
	protected void recordAction(boolean isProductive) {
		
	}
}