package mobile.android.send.sms.activity;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SendSMSActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void onClick_SendSMS(View view)
    {
    	
    	Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:12345678"));

    	sendIntent.putExtra("sms_body", "ÄãºÃÂð£¿");
    	startActivity(sendIntent); 
    }
}