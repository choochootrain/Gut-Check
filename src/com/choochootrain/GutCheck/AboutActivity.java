package com.choochootrain.GutCheck;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {
	private TextView versionLabel;
	private TextView source;
	private TextView issues;
	private Button close;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        versionLabel = (TextView)findViewById(R.id.about_version);
        try {
        	versionLabel.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        }
        catch(Exception e) {
        	Log.e(DEVICE_POLICY_SERVICE, "Version name not found.");
        	versionLabel.setText("version not found.");
        }
        
        source = (TextView)findViewById(R.id.about_source);
        source.setMovementMethod(LinkMovementMethod.getInstance());
        
        issues = (TextView)findViewById(R.id.about_issues);
        issues.setMovementMethod(LinkMovementMethod.getInstance());
        
        close = (Button)findViewById(R.id.about_close);
        close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
        	
        });
    }
}