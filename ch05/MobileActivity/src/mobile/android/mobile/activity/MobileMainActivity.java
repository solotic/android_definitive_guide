package mobile.android.mobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MobileMainActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mobile_main);
	}

	public void onClick_TabActivity(View view)
	{
		Intent intent = new Intent(this, TabActivity.class);
		
		startActivity(intent);
	}
	public void onClick_TabSwipeActivity(View view)
	{
		Intent intent = new Intent(this, TabSwipeActivity.class);
		startActivity(intent);
	}
	
	public void onClick_SwipeStripActivity(View view)
	{
		Intent intent = new Intent(this, SwipeStripActivity.class);
		startActivity(intent);
	}
	public void onClick_DropdownActivity(View view)
	{
		Intent intent = new Intent(this, DropdownActivity.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_mobile_main, menu);
		return true;
	}
}
