package mobile.android.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity
{
	private final int SPLASH_DISPLAY_LENGHT = 2000; // —”≥Ÿ¡Ω√Î

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		new Handler().postDelayed(new Runnable()
		{

			@Override
			public void run()
			{
				Intent mainIntent = new Intent(SplashActivity.this,
						MainActivity.class);
				SplashActivity.this.startActivity(mainIntent);
				SplashActivity.this.finish();
			}

		}, SPLASH_DISPLAY_LENGHT);
	}

}
