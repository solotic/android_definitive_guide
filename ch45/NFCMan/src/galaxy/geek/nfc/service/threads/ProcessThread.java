package galaxy.geek.nfc.service.threads;

import galaxy.geek.nfc.NFCMain;
import galaxy.geek.nfc.service.Data;
import galaxy.geek.nfc.util.SocketWorker;
import galaxy.geek.nfc.util.StringWorker;
import galaxy.geek.nfc.widget.MobileDevice;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import mobile.android.galaxy.geek.nfc.exceptions.MaxSizeException;
import mobile.android.galaxy.geek.nfc.interfaces.Const;

public class ProcessThread extends Thread
{
	private Socket mSocket;
	private NFCMain mNfcMain;
	private MobileDevice mMobileDevice;
	private InputStream mInputStream;
	private OutputStream mOutputStream;

	public ProcessThread(NFCMain nfcMain, Socket socket)
	{
		mSocket = socket;
		mNfcMain = nfcMain;
	}

	@Override
	public void run()
	{

		try
		{
			mInputStream = mSocket.getInputStream();
			mOutputStream = mSocket.getOutputStream();
			byte[] buffer = new byte[8192];
			int count = mInputStream.read(buffer);
			// 获取设备名
			final String deviceName = new String(buffer, 0, count, "utf-8");
			Data.putDevice(deviceName, mSocket);
			mNfcMain.shell.getDisplay().asyncExec(new Runnable()
			{
				public void run()
				{

					mMobileDevice = mNfcMain.addDevice(deviceName);

				}
			});

			while (true)
			{
				count = mInputStream.read(buffer);
				// 关闭连接，移除Device
				if (count == -1)
				{
					mNfcMain.shell.getDisplay().asyncExec(new Runnable()
					{
						public void run()
						{
							mMobileDevice.dispose();
							Data.removeDevice(deviceName);

						}
					});
					mSocket.close();
					mSocket = null;
					break;
				}
				final String clientText = new String(buffer, 0, count, "utf-8");
				// 一定要使用asyncExec方法执行writeDataToTag方法，因为该方法访问了其它线程创建的UI
				mNfcMain.shell.getDisplay().asyncExec(new Runnable()
				{
					public void run()
					{
						try
						{
							StringWorker.writeDataToTag(clientText);

							mOutputStream.write("success:".getBytes());
							mOutputStream.flush();
						}
						catch (MaxSizeException e)
						{
							try
							{

								//  将错误信息发送到客户端
								SocketWorker.sendExceptionToClient(mOutputStream, Const.NFC_MAN_EXCEPTION_MAX_SIZE, e.getMessage());
							}
							catch (Exception ee)
							{
								// TODO: handle exception
							}
						}
						catch (Exception e) {
							// TODO: handle exception
						}
					}
				});

			}

		}
		catch (final Exception e)
		{
		}
		finally
		{
			try
			{
				mOutputStream.close();
				mInputStream.close();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}

	}

}
