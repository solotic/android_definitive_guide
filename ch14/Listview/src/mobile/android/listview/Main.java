package mobile.android.listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends Activity implements OnItemSelectedListener,
		OnItemClickListener
{
	private static String[] data = new String[]
	{
			"天地逃生",
			"保持通话",
			"乱世佳人(飘)",
			"怪侠一枝梅",
			"第五空间",
			"孔雀翎",
			"变形金刚3（真人版）",
			"星际传奇"
			 };

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		Log.d("itemclick", "click " + position + " item");

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id)
	{
		Log.d("itemselected", "select " + position + " item");
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
		Log.d("nothingselected", "nothing selected");

	}



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView lvCommonListView = (ListView) findViewById(R.id.lvCommonListView);
		

		ArrayAdapter<String> aaData = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);

		lvCommonListView.setAdapter(aaData);
		lvCommonListView.setOnItemClickListener(this);
		lvCommonListView.setOnItemSelectedListener(this);


	}
}
