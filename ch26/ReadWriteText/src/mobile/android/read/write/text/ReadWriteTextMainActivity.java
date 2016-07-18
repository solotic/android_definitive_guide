package mobile.android.read.write.text;

import java.nio.charset.Charset;
import java.util.Locale;
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

public class ReadWriteTextMainActivity extends Activity
{
	private TextView mInputText;
	private String mText;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_write_text_main);
		mInputText = (TextView)findViewById(R.id.textview_input_text);

	}

	public void onClick_InputText(View view)
	{
		Intent intent = new Intent(this, InputTextActivity.class);
		startActivityForResult(intent, 1);
 
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == 1 && resultCode == 1)
		{
			mText = data.getStringExtra("text");
			mInputText.setText(mText);
		}
	}

	@Override
	public void onNewIntent(Intent intent)
	{
		if (mText == null)
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
			{ createTextRecord(mText) });

			if(writeTag(ndefMessage, tag))
			{
				mText = null;
				mInputText.setText("");
			}
			
		}

	}

	public NdefRecord createTextRecord(String text)
	{
		byte[] langBytes = Locale.CHINA.getLanguage().getBytes(
				Charset.forName("US-ASCII"));
		Charset utfEncoding = Charset.forName("UTF-8");
		byte[] textBytes = text.getBytes(utfEncoding);
		int utfBit = 0;
		char status = (char) (utfBit + langBytes.length);
		byte[] data = new byte[1 + langBytes.length + textBytes.length];
		data[0] = (byte) status;
		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length,
				textBytes.length);
		NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
				NdefRecord.RTD_TEXT, new byte[0], data);
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
