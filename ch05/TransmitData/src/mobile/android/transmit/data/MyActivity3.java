package mobile.android.transmit.data;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

public class MyActivity3 extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myactivity);
		TextView textView = (TextView) findViewById(R.id.textview);

		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
       
		String base64Str = clipboard.getPrimaryClip().getItemAt(0).getText()
				.toString();
		
		byte[] buffer = Base64.decode(base64Str, Base64.DEFAULT);

		ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		try
		{
			ObjectInputStream ois = new ObjectInputStream(bais);
			Data data = (Data) ois.readObject();
			textView.setText(base64Str + "\n\ndata.id£º" + data.id
					+ "\ndata.name£º" + data.name);
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}

}
