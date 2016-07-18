package mobile.android.custom.title;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class HideOldTitleBarActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hide_old_title_bar);
	} 
}
