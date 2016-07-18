package mobile.android.auto.run.application;

import java.net.URI;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AutoRunApplicationActivity extends Activity
{
	private Button mSelectAutoRunApplication;
	private String mPackageName;
	private NfcAdapter mNfcAdapter;
	private PendingIntent mPendingIntent;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_auto_run_application);
		mSelectAutoRunApplication = (Button) findViewById(R.id.button_select_auto_run_application);

		mNfcAdapter = mNfcAdapter.getDefaultAdapter(this);
		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()), 0);

	}

	@Override
	public void onResume()
	{
		super.onResume();
		if (mNfcAdapter != null)
			mNfcAdapter
					.enableForegroundDispatch(this, mPendingIntent, null, null);
	}

	@Override
	public void onNewIntent(Intent intent)
	{
		if (mPackageName == null)
			return;

		Tag detectedTag = intent.getParcelableExtra(mNfcAdapter.EXTRA_TAG);
		writeNFCTag(detectedTag);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		if (mNfcAdapter != null)
			mNfcAdapter.disableForegroundDispatch(this);

	}

	public void onClick_SelectAutoRunApplication(View view)
	{
		Intent intent = new Intent(this, InstalledApplicationListActivity.class);
		startActivityForResult(intent, 0);
	}

	public void writeNFCTag(Tag tag)
	{
		if (tag == null)
		{
			Toast.makeText(this, "NFC Tag未建立连接", Toast.LENGTH_LONG).show();
			return;
		}

		NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]
		{ NdefRecord.createApplicationRecord(mPackageName) });
		int size = ndefMessage.toByteArray().length;

		try
		{

			Ndef ndef = Ndef.get(tag);
			if (ndef != null)
			{
				ndef.connect();

				if (!ndef.isWritable())
				{
					Toast.makeText(this, "NFC Tag是只读的！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				if (ndef.getMaxSize() < size)
				{
					Toast.makeText(this, "NFC Tag的空间不足！", Toast.LENGTH_LONG)
							.show();
					return;
				}

				ndef.writeNdefMessage(ndefMessage);
				Toast.makeText(this, "已成功写入数据！", Toast.LENGTH_LONG).show();
			}
			else
			{
				NdefFormatable format = NdefFormatable.get(tag);
				if (format != null)
				{
					try
					{
						format.connect();
						format.format(ndefMessage);
						Toast.makeText(this, "已成功写入数据！", Toast.LENGTH_LONG).show();
					}
					catch (Exception e)
					{
						Toast.makeText(this, "写入NDEF格式数据失败！", Toast.LENGTH_LONG).show();
						
					}
				}
				else
				{
					Toast.makeText(this, "NFC标签不支持NDEF格式！", Toast.LENGTH_LONG).show();
				}
			}
		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == 1)
		{
			mSelectAutoRunApplication.setText(data.getExtras().getString(
					"package_name"));
			String temp = mSelectAutoRunApplication.getText().toString();
			mPackageName = temp.substring(temp.indexOf("\n") + 1);
			
		}

	}

}
