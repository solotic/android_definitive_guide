package mobile.android.galaxy.geek.nfc.service.threads;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import mobile.android.galaxy.geek.nfc.service.interfaces.Const;
import mobile.android.galaxy.geek.nfc.service.util.DealMessage;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SocketThread extends Thread implements Const
{
	private Socket mSocket;
	private OutputStream mOutputStream;
	private InputStream mInputStream;
	private String mHost;
	private String mDeviceName;
	private DealMessage mDealMessage;
	private Context mContext;
	private boolean mLoopFlag = true;

	public SocketThread(Context context, String host, String deviceName)
	{
		mHost = host;
		mDeviceName = deviceName;
		mContext = context;
		mDealMessage = new DealMessage(context);
	}

	@Override
	public void run()
	{

		try
		{

			mSocket = new Socket();

			mSocket.connect(new InetSocketAddress(mHost, NFC_MAN_SERVER_PORT),
					5000);
			mOutputStream = mSocket.getOutputStream();
			mInputStream = mSocket.getInputStream();
			// 向服务端传输设备名
			mOutputStream.write(mDeviceName.getBytes("utf-8"));
			mOutputStream.flush();

			byte[] buffer = new byte[8192];

			while (mLoopFlag)
			{
				int count = mInputStream.read(buffer);
				if(count == -1)
				{
					close();
					break;
				}
				
				String value = new String(buffer, 0, count, "utf-8");
				// 处理从服务端返回的数据
				mDealMessage.doInputMessage(value);
			}
  
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	public OutputStream getOutputStream()
	{
		return mOutputStream;
	}

	public InputStream getInputStream()
	{
		return mInputStream;
	}

	public void close()
	{
		try
		{
			mSocket.close();
			mLoopFlag = false;
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	public boolean isConnected()
	{
		return (mSocket != null) && !mSocket.isClosed()
				&& mSocket.isConnected();
	}
}
