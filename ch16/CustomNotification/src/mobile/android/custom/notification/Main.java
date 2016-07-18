package mobile.android.custom.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class Main extends Activity
{
	private NotificationManager mNotificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	public void onClick_ShowNotification(View view)
	{
		
		RemoteViews remoteViews = new RemoteViews(getPackageName(),
				R.layout.notification);
		remoteViews.setTextViewText(R.id.textview, "更新自定义内容");

		Notification.Builder builder = new Notification.Builder(this)
				.setSmallIcon(R.drawable.smile).setContent(remoteViews);

		mNotificationManager.notify(1, builder.build());

	}
}