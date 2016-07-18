package mobile.android.test.affinity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class MyActivity2 extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_activity2);
		setTitle("MyActivity2 Task ID:" + getTaskId());
		Log.d("MyActivity2", "onCreate");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_my_activity2, menu);
		return true;
	}


}
