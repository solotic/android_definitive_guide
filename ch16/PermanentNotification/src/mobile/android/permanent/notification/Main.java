package mobile.android.permanent.notification;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity
{
	private NotificationManager mNotificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancelAll();
	}
 
	public void onClick_ShowNotification(View view)
	{ 
	   
        Notification.Builder builder = new Notification.Builder(this)
        .setSmallIcon(R.drawable.smile).setContentTitle("短信内容").setOngoing(true)
        .setContentText("最近在忙什么？");
        mNotificationManager.notify(1, builder.build());
	}

}