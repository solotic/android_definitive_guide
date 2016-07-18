package mobile.android.multi.touch;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;

public class MultiTouchActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_touch);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		
		if (event.getPointerCount() == 2)
		{
			if (event.getAction() == MotionEvent.ACTION_MOVE)
			{
				int historySize = event.getHistorySize();
				if (historySize == 0)
					return true;
				float currentY1 = event.getY(0);
				float historyY1 = event.getHistoricalY(0, historySize - 1);

				float currentY2 = event.getY(1);
				float historyY2 = event.getHistoricalY(1, historySize - 1);
				float distance = Math.abs(currentY1 - currentY2);
				float historyDistance = Math.abs(historyY1 - historyY2);

				if (distance > historyDistance)
				{

					Log.d("status", "放大");
				}
				else if (distance < historyDistance)
				{
					Log.d("status", "缩小");
				}
				else
				{

					Log.d("status", "移动");
				}

			}
		}

		return true;
	}



}
