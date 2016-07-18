package mobile.android.verify.component;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class VerifyComponentActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verify_component);
	}

	public void onClick_VerifyActivityAction(View view)
	{
		PackageManager packageManager = getPackageManager();

		Intent intent = new Intent("com.android.phone.action.TOUCH_DIALER");
		List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(
				intent, PackageManager.GET_INTENT_FILTERS);
		if (resolveInfos.size() == 0)
			Toast.makeText(this, "com.android.phone.action.TOUCH_DIALER不可用",
					Toast.LENGTH_LONG).show();
		else
			Toast.makeText(this, "com.android.phone.action.TOUCH_DIALER可用",
					Toast.LENGTH_LONG).show();
	}

	public void onClick_VerifyBroadcastReceiver(View view)
	{
		PackageManager packageManager = getPackageManager();

		Intent intent = new Intent("mobile.android.MYBROADCAST");

		List<ResolveInfo> resolveInfos = packageManager
				.queryBroadcastReceivers(intent,
						PackageManager.GET_INTENT_FILTERS);

		if (resolveInfos.size() == 0)
			Toast.makeText(this, "mobile.android.MYBROADCAST不可用",
					Toast.LENGTH_LONG).show();
		else
			Toast.makeText(this, "mobile.android.MYBROADCAST可用",
					Toast.LENGTH_LONG).show();

	}

	private ServiceConnection serviceConnection = new ServiceConnection()
	{

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{

		}
	};

	public void onClick_VerifyAIDLService(View view)
	{
		if (!bindService(new Intent("mobile.android.MyService"),
				serviceConnection, BIND_AUTO_CREATE))
		{
			Toast.makeText(this, "mobile.android.MyService不可用",
					Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "mobile.android.MyService可用",
					Toast.LENGTH_LONG).show();
		}

	}

	public void onClick_VerifyContentProvider(View view)
	{
		Uri uri = Uri
				.parse("content://sms/inbox");
		
		Cursor cursor = getContentResolver().query(uri, null, "_id=-1",
				null, null);
		if (cursor == null)
		{
			Toast.makeText(this, "content://sms/inbox不可用",
					Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "content://sms/inbox可用",
					Toast.LENGTH_LONG).show();
		}

	}

}
