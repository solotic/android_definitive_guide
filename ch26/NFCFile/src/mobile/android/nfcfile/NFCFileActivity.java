package mobile.android.nfcfile;

import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateBeamUrisCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;

public class NFCFileActivity extends Activity implements CreateBeamUrisCallback
{
	private NfcAdapter mNfcAdapter;
	private PendingIntent mPendingIntent;
    private final String targetFilename = "/sdcard/temp_icon.png"; 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfcfile);
		mNfcAdapter = mNfcAdapter.getDefaultAdapter(this);
		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()), 0);

		mNfcAdapter.setBeamPushUrisCallback(this, this);

		try
		{
			InputStream is = getResources().getAssets().open("icon.png");
			FileOutputStream fos = new FileOutputStream(targetFilename);
			byte[] buffer = new byte[10000];
			int n = is.read(buffer);
			fos.write(buffer, 0, n);
			fos.close();
			is.close();
		}
		catch (Exception e)
		{

		}

	}

	@Override
	public Uri[] createBeamUris(NfcEvent event)
	{
		Uri[] uris = new Uri[1];
		Uri uri = Uri.parse("file://" + targetFilename);
				
		uris[0] = uri;

		return uris;
	}




}
