package mobile.android.custom.title;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeTitleBarActivity extends Activity
{
	private TextView mTextView;

	private void getTextView(ViewGroup vg)
	{
		int count = vg.getChildCount();
		for (int i = 0; i < count; i++)
		{
			View view = vg.getChildAt(i);
			if (view instanceof ViewGroup)
			{
				getTextView((ViewGroup) view);
			}
			else if (view instanceof View)
			{
				if (view instanceof TextView && mTextView == null)
					mTextView = (TextView) view;
			}
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_title_bar);

		Window w = getWindow();
		
		ViewGroup v = (ViewGroup) w.getDecorView();

		getTextView(v);
		if (mTextView != null)
		{
			mTextView.setText("标题文本已变化");
			mTextView.setTextColor(Color.RED);
			mTextView.setBackgroundColor(Color.BLUE);
			mTextView.setClickable(true);
			mTextView.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					Toast.makeText(ChangeTitleBarActivity.this, "单击标题文字",
							Toast.LENGTH_LONG).show();

				}
			});
		}
	}

}
