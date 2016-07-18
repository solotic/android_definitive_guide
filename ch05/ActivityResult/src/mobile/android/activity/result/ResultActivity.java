package mobile.android.activity.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResultActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_result);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode)
		{
			case 1:
				switch (resultCode)
				{
					case 2:
						setTitle(data.getStringExtra("value"));
						break;

					default:
						break;
				}
				break;

			default:
				break;
		}
	}

	public void onClick_ShowMyActivity(View view)
	{
		Intent intent = new Intent(this, MyActivity.class);
		startActivityForResult(intent, 1);
	}
}
