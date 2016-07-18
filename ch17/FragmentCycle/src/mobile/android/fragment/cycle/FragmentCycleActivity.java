package mobile.android.fragment.cycle;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class FragmentCycleActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_cycle);
		Log.d("Activity", "onCreate");

	}

	@Override
	protected void onStart()
	{
		Log.d("Activity", "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart()
	{
		Log.d("Activity", "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume()
	{
		Log.d("Activity", "onResume");
		super.onResume();
	}

	@Override  
	protected void onPause()
	{
		Log.d("Activity", "onPause");
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		Log.d("Activity", "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		Log.d("Activity", "onDestroy");
		super.onDestroy();
	}


}
