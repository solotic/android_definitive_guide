package mobile.android.intent.flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		setTitle(getTaskId() + "  " + String.valueOf(hashCode()));
		
	}  
	public void onClick_ShowMyActivity(View view)
	{
		Intent intent = new Intent(this, MyActivity.class);
		startActivity(intent);
	}

	public void onClick_CloseAllMyActivity(View view)
	{
		Intent intent = new Intent(this, IntentFlagActivity.class);
		//  重新建立IntentFlagActivity，并将其压栈
		//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		startActivity(intent);    
	}  
}  
