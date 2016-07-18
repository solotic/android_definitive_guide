package mobile.android.first.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class FirstActionBarActivity extends Activity
{
	private Button hideShowActionBar;

  
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_first_action_bar);
		hideShowActionBar = (Button) findViewById(R.id.button_hide_show_actionbar);
	
	}

	public void onClick_HideShowActionBar(View view)
	{

		if (getActionBar() == null)
			return;
		if (getActionBar().isShowing())
		{
			getActionBar().hide();
			hideShowActionBar.setText("ÏÔÊ¾ActionBar");
		}
		else
		{
			getActionBar().show();
			hideShowActionBar.setText("Òþ²ØActionBar");

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_first_action_bar, menu);
		//menu.getItem(2).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)  
	{
		Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
		return super.onOptionsItemSelected(item);
	}
	

}
