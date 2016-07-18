package mobile.android.invoke.system.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class InvokeSystemAppActivity extends Activity implements
		OnClickListener
{

	@Override
	public void onClick(View view)
	{
		try
		{

			switch (view.getId())
			{

				case R.id.btnCall:
					Intent callIntent = new Intent(Intent.ACTION_CALL,
							Uri.parse("tel:12345678"));
					startActivity(callIntent);
					break;
				case R.id.btnTouchDialer:
					Intent touchDialerIntent = new Intent(
							"com.android.phone.action.TOUCH_DIALER");
					startActivity(touchDialerIntent);
					break;
				case R.id.btnDial:
					Intent dialIntent = new Intent(Intent.ACTION_DIAL,
							Uri.parse("tel:87654321"));
					startActivity(dialIntent);
					break;
				case R.id.btnWeb:
					Intent webIntent = new Intent(Intent.ACTION_VIEW,
							Uri.parse("http://blog.csdn.net/nokiaguy"));
					startActivity(webIntent);

					break;

				case R.id.btnContactList:
					Intent contactListIntent = new Intent(
							"com.android.contacts.action.LIST_CONTACTS");
					startActivity(contactListIntent);
					break;
				case R.id.btnSettings:
					Intent settingsIntent = new Intent(
							"android.settings.SETTINGS");
					startActivity(settingsIntent);
					break;
				case R.id.btnWifiSettings:
					Intent wifiSettingsIntent = new Intent(
							"android.settings.WIFI_SETTINGS");
					startActivity(wifiSettingsIntent);
					break;
				case R.id.btnAudio:
					Intent audioIntent = new Intent(Intent.ACTION_GET_CONTENT);
					audioIntent.setType("audio/*");
					startActivity(Intent.createChooser(audioIntent, "—°‘Ò“Ù∆µ≥Ã–Ú"));
					break;
				case R.id.btnGoHome:
					Intent gohomeIntent = new Intent("android.intent.action.MAIN");
					gohomeIntent.addCategory("android.intent.category.HOME");
					startActivity(gohomeIntent);
					break;

				
			}

		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnCall = (Button) findViewById(R.id.btnCall);
		Button btnTouchDialer = (Button) findViewById(R.id.btnTouchDialer);
		Button btnDial = (Button) findViewById(R.id.btnDial);
		Button btnWeb = (Button) findViewById(R.id.btnWeb);
		Button btnAudio = (Button) findViewById(R.id.btnAudio);
		Button btnGoHome = (Button)findViewById(R.id.btnGoHome);
		Button btnContactList = (Button) findViewById(R.id.btnContactList);
		Button btnSettings = (Button) findViewById(R.id.btnSettings);
		Button btnWifiSettings = (Button) findViewById(R.id.btnWifiSettings);
		btnCall.setOnClickListener(this);
		btnTouchDialer.setOnClickListener(this);
		btnDial.setOnClickListener(this);
		btnWeb.setOnClickListener(this);
		btnAudio.setOnClickListener(this);
		btnGoHome.setOnClickListener(this);
		btnContactList.setOnClickListener(this);
		btnSettings.setOnClickListener(this);
		btnWifiSettings.setOnClickListener(this);
	}
}