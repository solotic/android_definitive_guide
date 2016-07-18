package mobile.android.test.affinity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class TestAffinityActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_affinity);
		setTitle("TestAffinityActivity Task ID:" + getTaskId());
		Intent intent = new Intent(this, MyActivity1.class);
		startActivity(intent);
		
	}


}
