package mobile.android.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity
{
	private NotificationManager mNotificationManager;

	public void onClick_ShowCommonNotification(View view)
	{
		Notification.Builder builder = new Notification.Builder(this)
				.setSmallIcon(R.drawable.smile).setContentTitle("笑脸通知")
				.setContentText("图标显示在左侧!");
		mNotificationManager.notify(1, builder.build());
	}

	public void onClick_ShowBigIconNotification(View view)
	{
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.smile);
		Notification.Builder builder = new Notification.Builder(this)
				.setSmallIcon(R.drawable.why).setLargeIcon(bitmap)
				.setContentTitle("大图标笑脸").setContentText("大图标放在左侧，小图标放在右下角!");

		mNotificationManager.notify(2, builder.build());

	}

	public void onClick_ShowBigPictureNotification(View view)
	{
		// 必须先清除大图Notification，否则再次显示时抛出异常
		mNotificationManager.cancel(3);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.big);

		Notification notification = new Notification.BigPictureStyle(
				new Notification.Builder(this).setContentTitle("Big Picture")
						.setContentText("大图").setSmallIcon(R.drawable.wrath))
				.bigPicture(bitmap).build();

		mNotificationManager.notify(3, notification);

	}

	public void onClick_ShowInfoNotification(View view)
	{
		Notification.Builder builder1 = new Notification.Builder(this)
				.setSmallIcon(R.drawable.why).setContentTitle("Info")
				.setContentText("文本信息显示在右下角!").setContentInfo("信息");

		mNotificationManager.notify(4, builder1.build());

		// setContentInfo优先，有setContentInfo，setNumber不起作用
		Notification.Builder builder2 = new Notification.Builder(this)
				.setSmallIcon(R.drawable.wrath).setContentTitle("Number")
				.setContentText("数字显示在右下角!").setNumber(25);
		mNotificationManager.notify(5, builder2.build());

	}

	public void onClick_ShowProgressNotification(View view)
	{
		Notification.Builder builder1 = new Notification.Builder(this)
				.setSmallIcon(R.drawable.smile).setContentTitle("Determinate Progress")
				.setContentText("显示进度的进度条!").setProgress(100, 25, false);
		mNotificationManager.notify(6, builder1.build());
		Notification.Builder builder2 = new Notification.Builder(this)
		.setSmallIcon(R.drawable.why).setContentTitle("Indeterminate Progress")
		.setContentText("未显示进度的进度条!").setProgress(100, 25, true);
		mNotificationManager.notify(7, builder2.build());
	}
    public void onClick_ClearAllNotification(View view)
    {
    	mNotificationManager.cancelAll();
    }
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}
}
