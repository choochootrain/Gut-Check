package com.choochootrain.GutCheck;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class GutCheckService extends Service {
	 
	  private static final String TAG = GutCheckService.class.getSimpleName();
	  private static final long UPDATE_INTERVAL = 5 * 1000L; //1 hour
	  private Timer timer;
	  private final IBinder mBinder = new ActivityBinder();
	 
	  private TimerTask updateTask = new TimerTask() {
	    @Override
	    public void run() {
	      Log.i(TAG, "Timer task doing work");
	      startNotification();
	    }
	  };
	 
	  @Override
	  public IBinder onBind(Intent intent) {
		  return mBinder;
	  }
	 
	  @Override
	  public void onCreate() {
	    super.onCreate();
	    Log.i(TAG, "Service creating");
	 
	    timer = new Timer("GutCheckTimer");
	    timer.schedule(updateTask, 1000L, UPDATE_INTERVAL);
	  }
	 
	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	    Log.i(TAG, "Service destroying");
	 
	    timer.cancel();
	    timer = null;
	  }
	  
	  public class ActivityBinder extends Binder {
		  GutCheckService getService() {
			  return GutCheckService.this;
		  }
	  }
	  
	  private void startNotification()
	  {
		  //get notification manager
		  String ns = Context.NOTIFICATION_SERVICE;
		  NotificationManager mNotificationManager = (NotificationManager)getSystemService(ns);
		  
		  //instantiate notification
		  int icon = R.drawable.ic_launcher;
		  CharSequence tickerText = "What are you doing right now";
		  long now = System.currentTimeMillis();
		  Notification notification = new Notification(icon, tickerText, now);
		  
		  //Define message and pending intent
		  Context context = getApplication();
		  CharSequence contentTitle = "Gut Check";
		  CharSequence contentText = "Am I being as productive as possible right now?";
		  Intent notificationIntent = new Intent(context, GutCheckActivity.class);
		  PendingIntent contentIntent= PendingIntent.getActivity(context, 0, notificationIntent, 0);
		  notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		  
		  //set options
		  notification.defaults |= Notification.DEFAULT_ALL;
		  notification.flags |= Notification.FLAG_NO_CLEAR;
		  
		  //pass notification to manager
		  int NOTIF_ID = 1;
		  mNotificationManager.notify(NOTIF_ID, notification);
	  }
}