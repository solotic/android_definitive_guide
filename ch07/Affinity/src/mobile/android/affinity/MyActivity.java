package mobile.android.affinity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		setTitle(String.valueOf(getTaskId()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_my, menu);
		return true;
	}
}
