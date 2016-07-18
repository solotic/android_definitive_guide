package mobile.android.aidl;
import mobile.android.aidl.IMyService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service
{ 
	
	public class MyServiceImpl extends IMyService.Stub
	{

		@Override
		public String getValue() throws RemoteException
		{
			// TODO Auto-generated method stub
			return "《Android深度探索（卷1）：HAL与驱动开发》";
		}
		
	}

	@Override
	public IBinder onBind(Intent intent)
	{		
		return new MyServiceImpl();
	}

	
}
