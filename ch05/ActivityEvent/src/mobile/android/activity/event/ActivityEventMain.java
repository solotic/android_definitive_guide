package mobile.android.activity.event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityEventMain extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_event_main);
	}

	public void onClick_SetTitle(View view)
	{
		setTitle("新窗口标题");
		setTitleColor(12345);
	}

	@Override
	public CharSequence onCreateDescription()
	{
		// TODO Auto-generated method stub
		return super.onCreateDescription();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		Log.d("onKeyDown:KeyCode", String.valueOf(keyCode));
		Log.d("onKeyDown:RepeatCount", String.valueOf(event.getRepeatCount()));
		if (event.getRepeatCount() == 200)
		{
			Toast.makeText(this, "已经按一会了，累了吧，该松开了！", Toast.LENGTH_LONG).show();
		}
		if (keyCode == KeyEvent.KEYCODE_MENU)
		{
			Log.d("onKeyDown", "MenuKey Down");

		}

		event.startTracking();

		return true;// super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event)
	{

		Log.d("onKeyLongPress", "onKeyLongPress_KeyCode：" + keyCode);
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_MENU)
		{
			Log.d("onKeyUp", "MenuKey Up");
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void onBackPressed()
	{
		Log.d("onBackPressed", "OK");
		super.onBackPressed();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{

		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			Log.d("onTouchEvent", "onTouchEvent_Action:Down");
		}
		else if (event.getAction() == MotionEvent.ACTION_UP)
		{
			Log.d("onTouchEvent", "onTouchEvent_Action:Up");
		}

		Log.d("onTouchEvent", "onTouchEvent_X：" + event.getX());
		Log.d("onTouchEvent", "onTouchEvent_Y：" + event.getY());

		return super.onTouchEvent(event);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		Log.d("ActivityEventMain", "onPostCreate");
		super.onPostCreate(savedInstanceState);
	}

	@Override
	protected void onTitleChanged(CharSequence title, int color)
	{
		super.onTitleChanged(title, color);

		Log.d("ActivityEventMain", "onTitleChanged_title:" + title);
		Log.d("ActivityEventMain", "onTitleChanged_color:" + color);

	}

	@Override
	public void onContentChanged()
	{
		// TODO Auto-generated method stub
		super.onContentChanged();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{

		if (event.getAction() == KeyEvent.ACTION_DOWN)
		{
			Log.d("displatchKeyEvent", "down");
		}
		else if (event.getAction() == KeyEvent.ACTION_UP)
		{
			Log.d("displatchKeyEvent", "up");
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		Log.d("onWindowFocusChanged",
				hasFocus ? "onWindowFocusChanged:has Focus"
						: "onWindowFocusChanged:hasn't focus");
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	protected void onResume()
	{
		Log.d("onResume", "onResume:has Focus");
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		Log.d("onPause", "onResume:hasn't Focus");
		super.onPause();
	}

	@Override
	protected void onStart()
	{
		Toast.makeText(this, "窗口已经恢复!", Toast.LENGTH_LONG).show();
		super.onStart();
	}

	private boolean isFinished;

	@Override
	protected void onStop()
	{
		if (!isFinished)
		{
			Toast.makeText(this, "窗口已经在后台运行（按了Home键）!", Toast.LENGTH_LONG)
					.show();
		}
		super.onStop();
	}

	@Override
	public void finish()
	{
		isFinished = true;

		super.finish();
	}

}
