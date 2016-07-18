package mobile.android.galaxy.geek.nfc.service.util;

import mobile.android.galaxy.geek.nfc.service.interfaces.Const;
import android.R.layout;
import android.content.Context;
import android.content.SharedPreferences;


public class Storage implements Const
{
	private Context mContext;
	public Storage(Context context)
	{
		mContext = context; 
	}
    public void putString(String key, String value)
    {
        SharedPreferences sp = mContext.getSharedPreferences(NFC_MAN_SETTINGS_NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
        
    }
    public String getString(String key, String defValue)
    {
        SharedPreferences sp = mContext.getSharedPreferences(NFC_MAN_SETTINGS_NAME,Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    	
    }
}
