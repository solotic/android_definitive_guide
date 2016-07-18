package mobile.android.android.beam;

import java.nio.charset.Charset;
import java.util.Locale;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidBeamMainActivity extends Activity implements
		CreateNdefMessageCallback,OnNdefPushCompleteCallback
{

	private EditText mBeamText;

	private NfcAdapter mNfcAdapter;
	private PendingIntent mPendingIntent;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_android_beam);
		mBeamText = (EditText) findViewById(R.id.edittext_beam_text);
		mNfcAdapter = mNfcAdapter.getDefaultAdapter(this);
		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()), 0);
		mNfcAdapter.setNdefPushMessageCallback(this, this);
		mNfcAdapter.setOnNdefPushCompleteCallback(this, this);

	}

	@Override
	public void onNdefPushComplete(NfcEvent event)
	{
		//  Toast.makeText(this, "消息传输完成", Toast.LENGTH_LONG).show();
		Log.d("message", "complete");
		
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent event)
	{
		String text = mBeamText.getText().toString().trim();
		if ("".equals(text))
		{
			text = "默认文本";
		}

		NdefMessage msg = new NdefMessage(new NdefRecord[]
		{ createTextRecord(text) });
		return msg;
	}

	@Override
	public void onResume()
	{
		super.onResume();
		if (mNfcAdapter != null)
			mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null,
					null);
		
	}

	@Override
	public void onPause()
	{
		super.onPause();
		if (mNfcAdapter != null)
			mNfcAdapter.disableForegroundDispatch(this);

	}

	@Override
	public void onNewIntent(Intent intent)
	{
		
		processIntent(intent);
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

	void processIntent(Intent intent)
	{

		Parcelable[] rawMsgs = intent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

		NdefMessage msg = (NdefMessage) rawMsgs[0];
		String text = TextRecord.parse(msg.getRecords()[0]).getText();
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
}
