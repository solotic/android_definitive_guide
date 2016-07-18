package mobile.android.read.write.uri;

import mobile.android.read.write.uri.library.UriRecord;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReadWriteUriMainActivity extends Activity
{
	private TextView mSelectUri;
	private String mUri;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_write_uri_main);
		mSelectUri = (TextView)findViewById(R.id.textview_uri);

	}

	public void onClick_SelectUri(View view)
	{
		Intent intent = new Intent(this, UriListActivity.class);
		startActivityForResult(intent, 1);
 
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == 1 && resultCode == 1)
		{
			mUri = data.getStringExtra("uri");
			mSelectUri.setText(mUri);
		}
	}

	@Override
	public void onNewIntent(Intent intent)
	{
		if (mUri == null)
		{
			Intent myIntent = new Intent(this, ShowNFCTagContentActivity.class);
			myIntent.putExtras(intent);
			myIntent.setAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
			startActivity(myIntent);
		}
		else
		{
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]
			{ createUriRecord(mUri) });

			if(writeTag(ndefMessage, tag))
			{
				mUri = null;
				mSelectUri.setText("");
			}
			
		}

	}

	public NdefRecord createUriRecord(String uriStr)
	{

		byte prefix = 0;
		for (Byte b : UriRecord.URI_PREFIX_MAP.keySet())
		{
			String prefixStr = UriRecord.URI_PREFIX_MAP.get(b).toLowerCase();
			if ("".equals(prefixStr))
				continue;
			if (uriStr.toLowerCase().startsWith(prefixStr))
			{
				prefix = b;
				uriStr = uriStr.substring(prefixStr.length());
				break;
			}
		}

		byte[] data = new byte[1 + uriStr.length()];
		data[0] = prefix;
		System.arraycopy(uriStr.getBytes(), 0, data, 1, uriStr.length());
		NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
				NdefRecord.RTD_URI, new byte[0], data);

		return record;
	}

	boolean writeTag(NdefMessage message, Tag tag)
	{
		int size = message.toByteArray().length;

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
					return false;

				}
				if (ndef.getMaxSize() < size)
				{
					Toast.makeText(this, "NFC Tag的空间不足！", Toast.LENGTH_LONG)
							.show();
					return false;
				}

				ndef.writeNdefMessage(message);
				Toast.makeText(this, "已成功写入数据！", Toast.LENGTH_LONG).show();
				return true;

			}
			else
			{
				NdefFormatable format = NdefFormatable.get(tag);
				if (format != null)
				{
					try
					{
						format.connect();
						format.format(message);
						Toast.makeText(this, "已成功写入数据！", Toast.LENGTH_LONG)
								.show();
						return true;
						
					}
					catch (Exception e)
					{
						Toast.makeText(this, "写入NDEF格式数据失败！", Toast.LENGTH_LONG)
								.show();
						return false;
					}
				}
				else
				{
					Toast.makeText(this, "NFC标签不支持NDEF格式！", Toast.LENGTH_LONG)
							.show();
					return false;

				}
			}
		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			return false;
		}

	}
}
