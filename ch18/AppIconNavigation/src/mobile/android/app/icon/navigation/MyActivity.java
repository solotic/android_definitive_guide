package mobile.android.app.icon.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MyActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		getActionBar().setHomeButtonEnabled(true);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

	    switch (item.getItemId()) {
	        case android.R.id.home:
	            Intent intent = new Intent(this, AppIconNavigationActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	           
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}



}
