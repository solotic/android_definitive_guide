package mobile.android.launch.mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StandardActivity extends Activity
{
  
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_standard);
	
		setTitle("Task ID:" + getTaskId() + " hashcode:" + this.hashCode());
	}


	public void onClick_Standard(View view)
	{
		Intent intent = new Intent(this, StandardActivity.class);
		startActivity(intent);
	}

}
