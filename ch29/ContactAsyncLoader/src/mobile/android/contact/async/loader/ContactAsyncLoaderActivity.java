package mobile.android.contact.async.loader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ContactAsyncLoaderActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_async_loader);
		String a;
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contact_async_loader, menu);
		return true;
	}

}
