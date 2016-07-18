package mobile.android.process;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProcessActivity extends Activity
{
	public static String getAllProcess(Context context)
	{
		StringBuilder sb = new StringBuilder();
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningAppProcessInfo rapi : am.getRunningAppProcesses())
		{
			
			sb.append(rapi.processName + "(" + rapi.pid + ")\n");
		}
		return sb.toString();
	}
    
	@Override  
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process);
		TextView textView = (TextView) findViewById(R.id.textview);
		String processId = String.valueOf(android.os.Process.myPid());

		textView.setText("当前窗口所在进程的ID：" + processId + "\n");
		textView.append("系统中运行的所有进程名称\n" + getAllProcess(this));
 
	}
    public void onClick_CreateNewProcess(View view)
    {
    	Intent intent = new Intent(this, ActivityA.class);
    	startActivity(intent);
    	
    }
    public void onClick_UseOldProcess(View view)
    {
    	Intent intent = new Intent(this, ActivityB.class);
    	startActivity(intent);
    	
    }
}
