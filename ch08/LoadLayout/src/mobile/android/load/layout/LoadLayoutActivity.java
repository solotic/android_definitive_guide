package mobile.android.load.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadLayoutActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout parent = (LinearLayout) getLayoutInflater().inflate(
				R.layout.activity_load_layout, null);
		for(int i = 1; i <= 10; i++)
		{
		    View view = getLayoutInflater().inflate(R.layout.item, null);
		    TextView textView = (TextView)view.findViewById(R.id.textview);
		    textView.setText("text" + i);
		    android.widget.LinearLayout.LayoutParams layoutParams = new android.widget.LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		    layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		    parent.addView(view, layoutParams);
		}    
		setContentView(parent);
	}

}
