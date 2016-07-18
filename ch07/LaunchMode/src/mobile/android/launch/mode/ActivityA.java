package mobile.android.launch.mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityA extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);  
		setTitle("ActivityA  Task ID:" + getTaskId());
	}
   
	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		Log.d("onNewIntent", "ActivityA_singleTask");
	} 
   
	public void onClick_ActivityB(View view)
	{
		Intent intent = new Intent(this, ActivityB.class);
		startActivity(intent);
		
	}
 
}
