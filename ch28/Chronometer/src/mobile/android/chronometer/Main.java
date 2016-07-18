package mobile.android.chronometer;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Chronometer.OnChronometerTickListener;

public class Main extends Activity implements OnClickListener,
		OnChronometerTickListener
{
	private Chronometer chronometer;
	private TextView tvTime;


	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public Object onRetainNonConfigurationInstance()
	{
		// TODO Auto-generated method stub
		return super.onRetainNonConfigurationInstance();
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.btnStart: 
				chronometer.start();
				break;
			case R.id.btnStop:
				chronometer.stop();
				break;
			case R.id.btnReset:
				chronometer.setBase(SystemClock.elapsedRealtime());
			
				break;
		}
	}

	@Override
	public void onChronometerTick(Chronometer chronometer)
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		tvTime.setText("当前时间：" + sdf.format(new Date()));

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvTime = (TextView)findViewById(R.id.tvTime);
		Button btnStart = (Button) findViewById(R.id.btnStart);
		Button btnStop = (Button) findViewById(R.id.btnStop);
		Button btnReset = (Button) findViewById(R.id.btnReset);
		chronometer = (Chronometer) findViewById(R.id.chronometer);
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnReset.setOnClickListener(this);
		chronometer.setOnChronometerTickListener(this);
		chronometer.setFormat("计时器：%s");
		
	}
}