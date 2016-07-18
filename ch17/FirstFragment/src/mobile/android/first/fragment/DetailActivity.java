package mobile.android.first.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity
{    
  
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		TextView detail = (TextView)findViewById(R.id.textview_detail);
		detail.setText(getIntent().getExtras().getString("detail"));

	}   

}
