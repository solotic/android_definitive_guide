package mobile.android.custom.title;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CustomTitleActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_title);
	}

	public void onClick_ChangeTitleBar(View view)
	{
		Intent intent = new Intent(this, ChangeTitleBarActivity.class);
		startActivity(intent);
	}

	public void onClick_HideOldAddNewTitleBar(View view)
	{
		Intent intent = new Intent(this, HideOldTitleBarActivity.class);
		startActivity(intent);
	}
    public void onClick_ChangeTheme(View view)
    {
    	Intent intent = new Intent(this, ThemeTitleActivity.class);
    	startActivity(intent);
    }
	public void onClick_ChangeTitleLayout(View view)
	{
		Intent intent = new Intent(this, ChangeTitleLayoutActivity.class);
		startActivity(intent);

	}
}
