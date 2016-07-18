package mobile.android.galaxy.geek.nfc.service;

import mobile.android.galaxy.geek.nfc.service.interfaces.Const;
import mobile.android.galaxy.geek.nfc.service.threads.SocketThread;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NFCManService extends Service implements Const
{
	private static SocketThread mSocketThread;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		if (intent == null)
			return super.onStartCommand(intent, flags, startId);
		String command = intent.getStringExtra("command");
		if (NFC_MAN_COMMAND_CLOSE_SOCKET.equals(command))
		{
			mSocketThread.close();
		}
		else
		// 默认开启线程
		{
			String host = intent.getStringExtra("host");
			String deviceName = intent.getStringExtra("deviceName");
			mSocketThread = new SocketThread(this, host, deviceName);
			mSocketThread.start();
		}
		return super.onStartCommand(intent, flags, startId);
	}

	public static SocketThread getSocketThread()
	{
		return mSocketThread;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
}
