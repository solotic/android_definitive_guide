package mobile.android.change.activity.property;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ChangeActivity extends Activity implements OnSeekBarChangeListener
{
    private SeekBar seekBar;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change);
		
		WindowManager.LayoutParams layoutParams = getWindow().getAttributes(); //获取对话框当前的参数值
		layoutParams.height =200; 
		layoutParams.width = 300; 
		layoutParams.y = 50;
		layoutParams.gravity = Gravity.TOP;
		getWindow().setAttributes(layoutParams);
		seekBar = (SeekBar)findViewById(R.id.seekbar);
		seekBar.setOnSeekBarChangeListener(this);
	}
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser)
	{
		WindowManager.LayoutParams layoutParams = getWindow().getAttributes(); //获取对话框当前的参数值
		layoutParams.alpha = (float)progress/100;
		getWindow().setAttributes(layoutParams);
		
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar)
	{
		
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar)
	{
		
		
	}


}
