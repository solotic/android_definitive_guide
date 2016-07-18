package mobile.android.galaxy.geek.nfc.test;

import mobile.android.galaxy.geek.nfc.NFCMan;
import mobile.android.galaxy.geek.nfc.activity.NFCActivity;
import mobile.android.galaxy.geek.nfc.data.NFCData;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NFCManTestActivity extends NFCActivity
{
	private NFCMan mNfcMan;
	private EditText nfcTagText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfcman_test);
		nfcTagText = (EditText) findViewById(R.id.edittext_nfc);
		mNfcMan = new NFCMan(this);

	}

	@Override
	public String onNear(NFCData data)
	{
		return nfcTagText.getText().toString();
	}

	@Override
	public void onNFCSuccess()  
	{
		Toast.makeText(this, "成功写入数据", Toast.LENGTH_LONG).show();

	}  
   
	@Override
	public void onNFCFailed(int error, String errorMsg)
	{
		Toast.makeText(this, "error:" + error + "\n" + "msg:" + errorMsg,
				Toast.LENGTH_LONG).show();
	}  

}
