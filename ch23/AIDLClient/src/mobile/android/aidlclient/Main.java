package mobile.android.aidlclient;

import mobile.android.aidl.IMyService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener
{
	private IMyService myService = null;
	private Button btnInvokeAIDLService;
	private Button btnBindAIDLService;
	private TextView textView;
	private ServiceConnection serviceConnection = new ServiceConnection()
	{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			myService = IMyService.Stub.asInterface(service);
			btnInvokeAIDLService.setEnabled(true);

		}

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			Toast.makeText(Main.this, "无法连接", Toast.LENGTH_SHORT).show();
			btnInvokeAIDLService.setEnabled(true);
		}
	};

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.btnBindAIDLService:
				if(!bindService(new Intent("mobile.android.aidl.IMyService"),
						serviceConnection, Context.BIND_AUTO_CREATE))
					Toast.makeText(Main.this, "无法连接", Toast.LENGTH_SHORT).show();
				
				break;

			case R.id.btnInvokeAIDLService:
				try
				{
					textView.setText(myService.getValue());
				}
				catch (Exception e)
				{
					textView.setText(e.getMessage());
				}
				break;
		}

	}  

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnInvokeAIDLService = (Button) findViewById(R.id.btnInvokeAIDLService);
		btnBindAIDLService = (Button) findViewById(R.id.btnBindAIDLService);
		btnInvokeAIDLService.setEnabled(false);
		textView = (TextView) findViewById(R.id.textview);
		btnInvokeAIDLService.setOnClickListener(this);
		btnBindAIDLService.setOnClickListener(this);
	}
}