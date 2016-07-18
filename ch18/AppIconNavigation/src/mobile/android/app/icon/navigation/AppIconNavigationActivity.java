package mobile.android.app.icon.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AppIconNavigationActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_icon_navigation);
	}
	public void onClick_ShowMyActivity(View view)
	{
		Intent intent = new Intent(this, MyActivity.class);
		startActivity(intent);
	}
}
