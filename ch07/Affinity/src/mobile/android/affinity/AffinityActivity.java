package mobile.android.affinity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AffinityActivity extends Activity
{
  
	@Override
	public void onCreate(Bundle savedInstanceState)
	{  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_affinity);
		setTitle("AffinityActivity Task ID£º" + getTaskId());
	}   


	public void onClick_TaskAffinity(View view)
	{
		try
		{
			Intent intent = new Intent("mobile.android.ACTION_MYACTIVITY2");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		catch (Exception e)
		{
			Toast.makeText(this, "TestAffinityÎ´°²×°", Toast.LENGTH_LONG).show();
		}
	}
}
