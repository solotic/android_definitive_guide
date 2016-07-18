package mobile.android.read.write.uri;

import mobile.android.read.write.uri.library.UriRecord;
import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

public class ShowNFCTagContentActivity extends Activity
{
	private TextView mTagContent;
	private Tag mDetectedTag;
	private String mTagText;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_nfctag_content);
		mTagContent = (TextView) findViewById(R.id.textview_tag_content);
		mDetectedTag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
		Ndef ndef = Ndef.get(mDetectedTag);
		mTagText = ndef.getType() + "\n最大数据容量：" + ndef.getMaxSize()
				+ " bytes\n\n";
		readNFCTag();
		mTagContent.setText(mTagText);
	}

	private void readNFCTag()
	{

		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()))
		{

			Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(
					NfcAdapter.EXTRA_NDEF_MESSAGES);

			NdefMessage ndefMessage = null;
			int contentSize = 0;
			if (rawMsgs != null)
			{

				if (rawMsgs.length > 0)
				{
					ndefMessage = (NdefMessage) rawMsgs[0];
					contentSize = ndefMessage.toByteArray().length;
				}
				else
				{
					return;
				}
			}
			try
			{

				NdefRecord record = ndefMessage.getRecords()[0];

				UriRecord uriRecord = UriRecord
						.parse(ndefMessage.getRecords()[0]);

				mTagText += uriRecord.getUri().toString() + "\n\nUri\n"
						+ contentSize + " bytes";

			}
			catch (Exception e)
			{
				mTagContent.setText(e.getMessage());
			}
		}

	}
}
