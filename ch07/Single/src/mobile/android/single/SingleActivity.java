package mobile.android.single;

import mobile.android.single.instance.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SingleActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_single_instance);
		setTitle("SingleActivity Task ID:" + String.valueOf(getTaskId()));
	}  

	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		Log.d("onNewIntent", "SingleIntance.SingleInstanceActivity");
	}

	public void onClick_ShowNewActivity(View view)
	{
		Intent intent = new Intent(this, NewActivity.class);
		startActivity(intent);
	}
}
