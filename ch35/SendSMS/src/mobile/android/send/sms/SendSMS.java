package mobile.android.send.sms;


import android.app.Activity;
import android.app.PendingIntent;
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
		smsManager.sendTextMessage("12345678", null, "你好吗?", null, null);
		Toast.makeText(this, "短信发送成功.", Toast.LENGTH_LONG).show();


	}
}