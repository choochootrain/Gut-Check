package com.choochootrain.GutCheck;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GutCheckActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //start background service
        startService(new Intent(GutCheckService.class.getName()));
        
        //get notification manager
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager)getSystemService(ns);
        
        //instantiate notification
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "What are you doing right now";
        long now = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, now);
        
        //Define message and pending intent
        Context context = getApplicationContext();
        CharSequence contentTitle = "Gut Check";
        CharSequence contentText = "Am I being as productive as possible right now?";
        Intent notificationIntent = new Intent(this, GutCheckActivity.class);
        PendingIntent contentIntent= PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        
        //set options
        notification.defaults |= Notification.DEFAULT_ALL;
        
        //pass notification to manager
        int NOTIF_ID = 1;
        mNotificationManager.notify(NOTIF_ID, notification);
    }
}