package mobile.android.galaxy.geek.nfc.receiver;

import mobile.android.galaxy.geek.nfc.NFCMan;
import mobile.android.galaxy.geek.nfc.activity.NFCActivity;
import mobile.android.galaxy.geek.nfc.interfaces.Const;
import android.R.layout;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NFCManBroadcastReceiver extends BroadcastReceiver implements Const
{

	@Override
	public void onReceive(Context context, Intent intent)
	{

		// 由NFC Man发出来的广播
		if (NFC_MAN_BROADCAST_ACTION.equals(intent.getAction()))
		{
			Intent intentActivity = new Intent(context, NFCMan.getContext()
					.getClass());
			intentActivity.putExtra("simulate", true);

			intentActivity.putExtras(intent);
			
			

			intentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intentActivity);

		}

	}
}
