package mobile.android.split.actionbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;

public class SplitActionBar extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_split_action_bar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{  

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_split_action_bar, menu);

		return true;
	}
}
