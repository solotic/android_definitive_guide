package mobile.android.launch.mode;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Task ID:" + getTaskId());
	}


	public void onClick_Standard(View view)
	{
		Intent intent = new Intent(this, StandardActivity.class);
		startActivity(intent);
	}

	public void onClick_SingleTop(View view)
	{
		Intent intent = new Intent(this, SingleTopActivity.class);
		startActivity(intent);
	}

	public void onClick_ActivityA(View view)
	{
		Intent intent = new Intent(this, ActivityA.class);
		startActivity(intent);
	}

	public void onClick_SingleInstance(View view)
	{ 
		Intent intent = new Intent("mobile.android.ACTION_SINGLE");
		startActivity(intent);
	}
}
