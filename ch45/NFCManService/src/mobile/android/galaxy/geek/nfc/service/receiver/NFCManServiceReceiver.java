package mobile.android.galaxy.geek.nfc.service.receiver;

import java.io.OutputStream;

import mobile.android.galaxy.geek.nfc.service.NFCManService;
import mobile.android.galaxy.geek.nfc.service.interfaces.Const;
import mobile.android.galaxy.geek.nfc.service.threads.SocketThread;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//  ANFCMan也可以通过广播返回处理结果，但目前使用了AIDL Service
public class NFCManServiceReceiver extends BroadcastReceiver implements Const
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		if(NFC_MAN_SERVICE_BROADCAST_ACTION.equals(intent.getAction()))
		{  

			SocketThread socketThread = NFCManService.getSocketThread();
			if (socketThread != null)
			{
				try  
				{
					String msg = intent.getStringExtra("result");
					
					OutputStream os = socketThread.getOutputStream();
					os.write(msg.getBytes("utf-8"));
					os.flush();
		
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}

			}
		}
		
	}

}
