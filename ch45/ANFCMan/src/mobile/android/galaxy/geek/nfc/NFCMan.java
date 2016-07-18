package mobile.android.galaxy.geek.nfc;

import mobile.android.galaxy.geek.nfc.interfaces.Const;
import android.content.Context;

public class NFCMan implements Const
{
	
	private static Context mContext;
	

	public NFCMan(Context context)
	{
		mContext = context;
	}

	
	public static Context getContext()
	{
		return mContext;
	}

}
