package mobile.android.launch.mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SingleTopActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_top);
		setTitle("Task ID:" + getTaskId() + " hashcode:" + this.hashCode());
	}

	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		Log.d("singleTop", "onNewIntent");
	}

	public void onClick_SingleTop(View view)
	{
		Intent intent = new Intent(this, SingleTopActivity.class);
		startActivity(intent);
	}

}
