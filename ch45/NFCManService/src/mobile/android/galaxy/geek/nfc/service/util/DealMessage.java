package mobile.android.galaxy.geek.nfc.service.util;

import mobile.android.galaxy.geek.nfc.service.interfaces.Const;
import android.content.Context;
import android.content.Intent;

public class DealMessage implements Const
{
	private Context mContext;

	public DealMessage(Context context)
	{
		mContext = context;
	}

	public void doInputMessage(String msg)
	{
		Intent intent = new Intent(NFC_MAN_BROADCAST_ACTION);
		if (msg.startsWith("exception:"))
		{
			intent.putExtra("exception", msg.substring("exception:".length()));
		}
		else if(msg.startsWith("success:"))
		{
			intent.putExtra("success", "true");
		}
		else
		{
			// example: near:tag:test1 标签已经靠近名为test1的移动设备
			String[] array = msg.split(":");
			if (array.length < 4)
				return;

			String command = array[0];
			String type = array[1];
			String deviceName = array[2];
			String targetName = array[3]; // 可能为NFC标签名，也可能为移动设备名，该值由type的值决定

			
			if ("near".equals(command))
			{
				// NFC标签靠近了移动设备
				if ("tag".equals(type))
				{
					
					intent.putExtra("command", command);
					intent.putExtra("type", type);
					intent.putExtra("deviceName", deviceName);
					intent.putExtra("targetName", targetName);

				 
				}  
			}
		}
		mContext.sendBroadcast(intent);
	}
}
