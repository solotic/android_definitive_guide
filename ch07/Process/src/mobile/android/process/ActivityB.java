package mobile.android.process;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ActivityB extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.activity_activity_b);
		TextView textView = (TextView) findViewById(R.id.textview);
		String processId = String.valueOf(android.os.Process.myPid());

		textView.setText("当前窗口所在进程的ID：" + processId + "\n");
		textView.append("系统中运行的所有进程名称\n"
				+ ProcessActivity.getAllProcess(this));
	}

}
