package mobile.android.screenorientation;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class ScreenOrientationActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_orientation);
	}

	public void onClick_FourScreenOrientation(View view)
	{
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

	}

	public void onClick_LandscapeOrientation(View view)
	{
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}

	public void onClick_PortraitOrientation(View view)
	{
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}

}
