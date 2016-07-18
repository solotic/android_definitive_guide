package galaxy.geek.nfc.service;

import galaxy.geek.nfc.NFCMain;
import galaxy.geek.nfc.interfaces.Const;
import galaxy.geek.nfc.service.threads.ProcessThread;

import java.net.ServerSocket;
import java.net.Socket;

public class NFCManServer  implements Runnable,Const
{
	private ServerSocket mServerSocket;
    private NFCMain mNfcMain;
    private boolean mStopFlag = false;
    public NFCManServer(NFCMain NfcMain)
    {
    	mNfcMain = NfcMain;
    	
    }
    
	@Override
	public void run()
	{
		if (mServerSocket == null)
		{
			try
			{
				mServerSocket = new ServerSocket(NFC_MAN_SERVER_PORT);
				while(!mStopFlag)
				{
					Socket socket = mServerSocket.accept();
					new ProcessThread(mNfcMain, socket).start();
					
				}
				
				
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}

		}
		
	}

	public void start()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
}
