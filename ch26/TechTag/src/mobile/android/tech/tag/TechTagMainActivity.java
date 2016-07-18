package mobile.android.tech.tag;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class TechTagMainActivity extends Activity
{ 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tech_tag_main);
        Log.d("aaaaaa", "eddd");
	}
  

	@Override
	public void onNewIntent(Intent intent)
	{

		Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		String[] techList = tag.getTechList();
		String techStr = "";
		for(String tech: techList)
		{
			techStr += tech + "\n";
		}
		
		Toast.makeText(this, "“—ºÏ≤‚µΩNFC±Í«©\n\n" + techStr, Toast.LENGTH_LONG).show();

	}

}
