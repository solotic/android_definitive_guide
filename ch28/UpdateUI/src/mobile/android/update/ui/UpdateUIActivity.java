package mobile.android.update.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class UpdateUIActivity extends Activity
{
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_ui);
		mTextView = (TextView) findViewById(R.id.textView1);
	}

	public class NoHandler implements Runnable
	{

		@Override
		public void run()
		{
			try
			{
				mTextView.setText("No Handler");
	
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
			}

		}

	}

	public class MyHandler implements Runnable
	{
		private Handler mHandler;

		public MyHandler(Handler handler)
		{
			mHandler = handler;
		}

		@Override
		public void run()
		{

			Message message = new Message();
			message.obj = "Handler";
			mHandler.sendMessage(message);
		}

	}

	public void onClick_NoHandler(View view)
	{
	    //mHandler.post(new NoHandler());
		Thread thread = new Thread(new NoHandler());
		thread.start();
	}
	Handler mHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			String str = String.valueOf(msg.obj);
			mTextView.setText(str);

		}
		
	};
	public void onClick_Handler(View view)
	{
		Thread thread = new Thread(new MyHandler(mHandler));
		thread.start();
		
		
	}

}  
