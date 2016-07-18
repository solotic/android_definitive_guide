package mobile.android.launch.mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityB extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		setTitle("ActivityB Task ID:" + getTaskId());
	}  


	public void onClick_ActivityA(View view)
	{
		Intent intent = new Intent(this, ActivityA.class);
		startActivity(intent);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d("onDestroy", "ActivityB_standard");
	}

}
