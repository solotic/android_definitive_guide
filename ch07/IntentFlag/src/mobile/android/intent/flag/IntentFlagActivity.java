package mobile.android.intent.flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class IntentFlagActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_flag);
		setTitle(getTaskId() + "   " + hashCode());
	}

	public void onClick_ShowMyActivity(View view)
	{
		Intent intent = new Intent(this, MyActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		Log.d("IntentFlagActivity", "onNewIntent");
	}

	public void onClick_OtherAppActivity_Standard(View view)
	{
		try
		{
			Intent intent = new Intent("mobile.android.ACTION_NEW_ACTIVITY");
			startActivity(intent);
		}
		catch (Exception e)
		{
			Toast.makeText(this, "未安装Single程序！", Toast.LENGTH_LONG).show();
		}

	}

	public void onClick_OtherAppActivity_SingleTask(View view)
	{
		try
		{
			Intent intent = new Intent("mobile.android.ACTION_NEW_ACTIVITY");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		catch (Exception e)
		{
			Toast.makeText(this, "未安装Single程序！", Toast.LENGTH_LONG).show();
		}
	}

	public void onClick_ShowIntentFlagActivity_SingleTop(View view)
	{

		Intent intent = new Intent(this, IntentFlagActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
	}
}
