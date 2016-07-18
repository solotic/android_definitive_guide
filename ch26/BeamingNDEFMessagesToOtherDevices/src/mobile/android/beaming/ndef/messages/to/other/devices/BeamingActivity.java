package mobile.android.beaming.ndef.messages.to.other.devices;

import java.nio.charset.Charset;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BeamingActivity extends Activity implements
		CreateNdefMessageCallback
{
	NfcAdapter nfcAdapter;

	// /////////////////event method///////////////////
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beaming);
	    
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter == null)
		{
			Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG)
					.show();
			finish();
			return;
		}
		// Register callback
		// nfcAdapter.setNdefPushMessageCallback(this, this);
	}

	public void onClick_StartApplication(View view)
	{
		nfcAdapter.setNdefPushMessageCallback(this, this);
		Toast.makeText(this, "ok", Toast.LENGTH_LONG).show();
	}

	// //////////////////////////////////////////////////
	@Override
	public NdefMessage createNdefMessage(NfcEvent event)
	{
		NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]
		{ NdefRecord.createApplicationRecord("com.android.calculator2") });
		
		/* String text = ("Beam me up, Android!\n\n" +
	                "Beam Time: " + System.currentTimeMillis());
	        NdefMessage ndefMessage = new NdefMessage(
	                new NdefRecord[] { createMimeRecord(
	                        "application/com.android.calculator2", text.getBytes())});*/
//Log.d("3333333", "222");
		
		
		return ndefMessage;
	}

	public NdefRecord createMimeRecord(String mimeType, byte[] payload)
	{
		byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
		NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
				mimeBytes, new byte[0], payload);
		return mimeRecord;
	}
}
