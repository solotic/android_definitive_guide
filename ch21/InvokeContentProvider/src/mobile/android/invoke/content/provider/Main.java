package mobile.android.invoke.content.provider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Show_Cities(View view)
	{
		ContentResolver contentResolver = getContentResolver();
		Uri uri = Uri
				.parse("content://mobile.android.regioncontentprovider/cities");
		Cursor cursor = contentResolver.query(uri, new String[]
		{ "city_code as _id", "city_name", "province_code" }, null, null, null);

		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, new String[]
				{ "city_name" }, new int[]
				{ android.R.id.text1 });

		ListView lvCities = (ListView) findViewById(R.id.lvCities);
		lvCities.setAdapter(simpleCursorAdapter);

		uri = Uri
				.parse("content://mobile.android.regioncontentprovider/code/024");
		cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor.moveToFirst())
		{
			Toast.makeText(
					this,
					"024��"
							+ cursor.getString(cursor
									.getColumnIndex("city_name")),
					Toast.LENGTH_LONG).show();
		}

		uri = Uri
				.parse("content://mobile.android.regioncontentprovider/name/����");
		cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor.moveToFirst())
		{
			Toast.makeText(
					this,
					"������"
							+ cursor.getString(cursor
									.getColumnIndex("city_code")),
					Toast.LENGTH_LONG).show();
		}
	}

	public void onClick_Show_Lining_Cities(View view)
	{
		ContentResolver contentResolver = getContentResolver();
		Uri uri = Uri
				.parse("content://mobile.android.regioncontentprovider/cities_in_province/����");
		Cursor cursor = contentResolver.query(uri, new String[]
		{ "city_code as _id", "city_name", "province_code" }, null, null,
				"city_code");

		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, new String[]
				{ "city_name" }, new int[]
				{ android.R.id.text1 });

		ListView lvCities = (ListView) findViewById(R.id.lvCities);
		lvCities.setAdapter(simpleCursorAdapter);
	}
}