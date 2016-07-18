package mobile.android.mms.browser;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MMSList extends ListActivity implements OnItemClickListener
{
	private Cursor cursor;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		cursor = getContentResolver().query(Uri.parse("content://mms"), null,
				null, null, null);

		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, new String[]
				{ "sub" }, new int[]
				{ android.R.id.text1 })
		{

			@Override
			public void setViewText(TextView v, String text)
			{
				try
				{
					text = new String(text.getBytes("ISO-8859-1"), "UTF-8");
				}
				catch (Exception e)
				{

				}
				super.setViewText(v, text);
			}
		};
		setListAdapter(simpleCursorAdapter);
		getListView().setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		cursor.moveToPosition(position);
		String mid = cursor.getString(cursor.getColumnIndex("_id"));
		Intent intent = new Intent(this, MMSBrowser.class);
		intent.putExtra("mid", mid);
		startActivity(intent);

	}

}