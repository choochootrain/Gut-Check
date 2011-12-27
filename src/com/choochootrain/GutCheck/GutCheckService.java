package com.choochootrain.GutCheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.choochootrain.GutCheck.Item.Item;

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
	  private static final int NOTIF_ID = 0;
	  private static int ITEM_ID = 0;
	  public static ArrayList<Item> PENDING_ITEMS = new ArrayList<Item>();
	  private Notification notification;
	  
	  private TimerTask updateTask = new TimerTask() {
	    @Override
	    public void run() {
	      Log.i(TAG, "Timer task doing work");
	      startNotification(new Date());
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
	  
	  private void startNotification(Date time)
	  {		  
		  //get notification manager
		  String ns = Context.NOTIFICATION_SERVICE;
		  NotificationManager mNotificationManager = (NotificationManager)getSystemService(ns);

		  //create new notification or update exisiting one
		  if(PENDING_ITEMS.size() == 0) {
			  //instantiate notification
			  int icon = R.drawable.ic_launcher;
			  CharSequence tickerText = getString(R.string.notification_ticker);
			  notification = new Notification(icon, tickerText, time.getTime());
			  
			  //set options
			  notification.defaults |= Notification.DEFAULT_ALL;
			  notification.flags |= Notification.FLAG_AUTO_CANCEL;
		  }
		  
		  //add pending item
		  ITEM_ID++;
		  PENDING_ITEMS.add(new Item(ITEM_ID, time));
		  
		  //Define message and pending intent
		  Context context = getApplication();
		  CharSequence contentTitle = getString(R.string.notification_title);
		  CharSequence contentText = getString(R.string.notification_text) + "\n" + getItemString();
		  Intent notificationIntent = new Intent(context, GutCheckActivity.class);
		  PendingIntent contentIntent= PendingIntent.getActivity(context, 0, notificationIntent, 0);
		  notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		  
		  //pass notification and id to manager
		  mNotificationManager.notify(NOTIF_ID, notification);
	  }

	private String getItemString() {
		if(PENDING_ITEMS.size() == 1)
			return PENDING_ITEMS.size() + " " + getString(R.string.notification_multiple);
		else
			return PENDING_ITEMS.size() + " " + getString(R.string.notification_multiple) + "s";
	}
}