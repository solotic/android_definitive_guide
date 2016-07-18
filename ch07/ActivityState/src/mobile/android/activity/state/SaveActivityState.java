package mobile.android.activity.state;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SaveActivityState extends Activity
{
	private String value = "default";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if (savedInstanceState != null)
		{
			value = savedInstanceState.getString("value");
		}
	}

	public void onClick_SetFieldValue(View view)

	{
		value = "newValue";
	}
	public void onClick_OutputFieldValue(View view)
	{
		Log.d("value", String.valueOf(value));
	}
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{

		super.onSaveInstanceState(outState);
		outState.putString("value", value);
	}

}