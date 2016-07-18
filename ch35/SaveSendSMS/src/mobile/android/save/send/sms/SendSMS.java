package mobile.android.save.send.sms;

import java.util.Date;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class SendSMS extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_SendSMS(View view)
	{
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage("123456", null, "你好吗?", null, null);
		Toast.makeText(this, "短信发送成功.", Toast.LENGTH_LONG).show();

		Uri uri = Uri.parse("content://mms-sms/canonical-addresses");

		Cursor cursor = getContentResolver().query(uri, null, "address=?",
				new String[]
				{ "123456" }, null);
		
		String threadID = "";

		if (cursor.moveToNext())
		{
			threadID = cursor.getString(0);
		}
		else  
		{
			return;
		}


		ContentValues contentValues = new ContentValues();
		contentValues.put("thread_id", threadID);
		contentValues.put("body", "你好吗？");
		contentValues.put("date", new Date().getTime());
		contentValues.put("address", 3544545);
		contentValues.put("type", 2);
		contentValues.put("read", 1);
		getContentResolver().insert(Uri.parse("content://sms"), contentValues);
	}
}