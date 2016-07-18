package mobile.android.distance;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class DistanceActivity extends Activity
{
    private EditText mEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distance);
		mEditText = (EditText)findViewById(R.id.edittext);
		
		final float[] results= new float[3];
		
		Location.distanceBetween(20.123, 30.05644, 30.124, 40.543, results);
		final float bearing = results[1];
		mEditText.setText(String.valueOf(results[0]) + "รื");
	}

	

}
