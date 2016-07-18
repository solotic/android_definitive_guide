package mobile.android.test.affinity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MyActivity1 extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_activity1);
		setTitle("MyActivity1 Task ID:" + getTaskId());
		Intent intent = new Intent(this, MyActivity2.class);
		startActivity(intent);
		Log.d("MyActivity1", "onCreate");

	}

 
	@Override
	protected void onNewIntent(Intent intent)
	{
		Log.d("MyActivity1", "onNewIntent");
		super.onNewIntent(intent);
	}

}
