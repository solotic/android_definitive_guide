package galaxy.geek.nfc.util;

import java.io.OutputStream;

public class SocketWorker
{
	public static void sendExceptionToClient(OutputStream os, int error,
			String errorMsg)
	{
		try
		{
			String msg = "exception:" + error + ":" + errorMsg;
			os.write(msg.getBytes("utf-8"));
			os.flush();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}
