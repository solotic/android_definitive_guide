package mobile.android.close.all.activity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CloseAllActivity extends Activity
{
	private static List<Activity> activities = new ArrayList<Activity>();
	private static int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_close_all);
		if (count == 0)
		{
			setTitle("Ö÷´°¿Ú");
			count++;
		}
		else
		{
			setTitle("´°¿Ú" + count);
			count++;
		}
		activities.add(this);
	}

	public void onClick_ShowNewActivity(View view)
	{
		Intent intent = new Intent(this, CloseAllActivity.class);
		startActivity(intent);
	}

	public void onClick_CloseAllActivity(View view)
	{
		for (int i = 1; i < activities.size(); i++)
		{
			if (activities.get(i) != null)
			{
				activities.get(i).finish();
			}
		}
		count = 1;
	}
}
