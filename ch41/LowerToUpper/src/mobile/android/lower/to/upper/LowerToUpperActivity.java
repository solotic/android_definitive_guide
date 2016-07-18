package mobile.android.lower.to.upper;

import android.app.Activity;
import android.os.Bundle;

public class LowerToUpperActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lower_to_upper);
		new LowerToUpper().convert("/sdcard/lower.txt", "/sdcard/upper.txt");
		setTitle("×ª»»³É¹¦");
	}
        


}
