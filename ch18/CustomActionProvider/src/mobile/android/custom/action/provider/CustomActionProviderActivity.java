package mobile.android.custom.action.provider;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class CustomActionProviderActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_action_provider);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		getMenuInflater().inflate(R.menu.activity_custom_action_provider, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_provider1:
				Toast.makeText(this, "menu_provider1", Toast.LENGTH_LONG).show();
				break;
			case R.id.menu_provider2:
				Toast.makeText(this, "menu_provider2", Toast.LENGTH_LONG).show();
				return true;
				

		}
		return super.onOptionsItemSelected(item);
	}

}
