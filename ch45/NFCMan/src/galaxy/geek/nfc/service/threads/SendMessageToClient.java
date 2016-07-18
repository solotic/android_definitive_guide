package galaxy.geek.nfc.service.threads;

import galaxy.geek.nfc.service.Data;

import java.io.OutputStream;
import java.net.Socket;

public class SendMessageToClient extends Thread
{
	private String mDeviceName;
	private String mText;

	public SendMessageToClient(String deviceName, String text)
	{
		mDeviceName = deviceName;
		mText = text;
	}

	@Override
	public void run()
	{
		Socket socket = Data.getDevice(mDeviceName);
		if (socket != null && socket.isConnected())
		{
			try
			{  
				OutputStream os = socket.getOutputStream();
				os.write(mText.getBytes("utf-8"));
				os.flush();
			
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}

	}

}
