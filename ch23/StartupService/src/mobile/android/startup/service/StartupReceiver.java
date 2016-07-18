package mobile.android.startup.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Intent serviceIntent = new Intent(context, MyService.class);		
		context.startService(serviceIntent);		
		Log.d("start_server", "ok");
	}
}
