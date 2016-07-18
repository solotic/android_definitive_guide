package mobile.android.read.write.uri;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

public class UriListActivity extends ListActivity implements
		OnItemClickListener
{
	private String uris[] = new String[]
	{ "http://www.google.com", "http://www.apple.com",
			"http://developer.apple.com", "http://www.126.com",
			"ftp://192.168.17.160", "https://192.168.17.120",
			"smb://192.168.17.100" };

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, uris);
		setListAdapter(arrayAdapter);
		getListView().setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		Intent intent = new Intent();
		intent.putExtra("uri", uris[position]);
		setResult(1, intent);
		finish();


	}

}
