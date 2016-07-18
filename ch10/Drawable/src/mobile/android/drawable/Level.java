package mobile.android.drawable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Level extends Activity {

	private ImageView ivLamp;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);

		ivLamp = (ImageView) findViewById(R.id.imageview_lamp);
		ivLamp.setImageLevel(8);
	}
	public void onClick_LampOn(View view)
	{
		ivLamp.setImageLevel(15);
	}

	public void onClick_LampOff(View view)
	{
		ivLamp.getDrawable().setLevel(6);

	}
}
 