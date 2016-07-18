package mobile.android.config.changes;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ConfigChangesActivity extends Activity
{
	private String name = "default";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config_changes);
		Log.d("invoked method", "onCreate");
	}

	@Override
	protected void onDestroy()
	{
		Log.d("invoked method", "onDestroy");
		super.onDestroy();
	}
    public void onClick_SetVar(View view)
    {
    	name = "android";
    }
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
			Log.d("orientation", "landscape");
		else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
			Log.d("orientation", "portrait");
	
		Log.d("screen_width_dp", String.valueOf(newConfig.screenWidthDp));
		Log.d("screen_height_dp", String.valueOf(newConfig.screenHeightDp));
	
		Log.d("name", name);
	}  

}
