package mobile.android.galaxy.geek.nfc.service;

import mobile.android.galaxy.geek.nfc.service.interfaces.Const;
import mobile.android.galaxy.geek.nfc.service.threads.SocketThread;
import mobile.android.galaxy.geek.nfc.service.util.Storage;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NFCManServiceActivity extends Activity implements Const
{
	private Storage mStorage;
	private EditText mServerIPEditText;
	private EditText mDeviceNameEditText;
	private Button mConnectButton;
	private Button mDisconnectButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfcman_service);
		initObject();
		initView();

	}

	private void initObject()
	{
		mStorage = new Storage(this);
	}

	private void initView()
	{
		mServerIPEditText = (EditText) findViewById(R.id.edittext_server_ip);
		mDeviceNameEditText = (EditText) findViewById(R.id.edittext_device_name);
		mConnectButton = (Button)findViewById(R.id.button_connect);
		mDisconnectButton = (Button)findViewById(R.id.button_disconnect);
		
		mServerIPEditText.setText(mStorage.getString(NFC_MAN_KEY_SERVER_IP, ""));
		mDeviceNameEditText.setText(mStorage.getString(NFC_MAN_KEY_DEVICE_NAME, ""));
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		SocketThread socketThread = NFCManService.getSocketThread();
        if(socketThread != null && socketThread.isConnected())
        {
        	mConnectButton.setEnabled(false);
        	mDisconnectButton.setEnabled(true);
        }
        else
        {
        	mConnectButton.setEnabled(true);
        	mDisconnectButton.setEnabled(false);
        }
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mStorage.putString(NFC_MAN_KEY_SERVER_IP, mServerIPEditText.getText()
				.toString());
		mStorage.putString(NFC_MAN_KEY_DEVICE_NAME, mDeviceNameEditText.getText()
				.toString());
	}

	@Override
	protected void onDestroy()
	{
		mStorage = null;
		super.onDestroy();
	}
  
	// event
	public void onClick_Connect(View view)
	{

		Intent intent = new Intent(this, NFCManService.class);
		intent.putExtra("host", mServerIPEditText.getText().toString());
		intent.putExtra("deviceName", mDeviceNameEditText.getText().toString());
		startService(intent);
		mConnectButton.setEnabled(false);
		mDisconnectButton.setEnabled(true);
	}
	
	public void onClick_Disconnect(View view)
	{
		Intent intent = new Intent(this, NFCManService.class);
		intent.putExtra("command", NFC_MAN_COMMAND_CLOSE_SOCKET);
		startService(intent);
		mConnectButton.setEnabled(true);
		mDisconnectButton.setEnabled(false);
	}
	// ////////////////

}
