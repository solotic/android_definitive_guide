package mobile.android.javascript.demo;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class JSActivity extends Activity
{
	private Button button;
	private WebView webView;
	private String startRandomMoveJavascript;
	private String stopRandomMoveJavascript;

	private Handler moveHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
	
			int x = msg.arg1;
			int y = msg.arg2;
			button.layout(x, y, button.getMeasuredWidth() + x,
					button.getMeasuredHeight() + y);
			super.handleMessage(msg);
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button) findViewById(R.id.button);
		webView = new WebView(this);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		
		webView.addJavascriptInterface(new Object()
		{

			public void move(int x, int y)
			{
				Message message = new Message();
				message.arg1 = x;
				message.arg2 = y;
				moveHandler.sendMessage(message);

			}
		}, "demo");

		// ¶Ájavascript´úÂë
		InputStream is = getResources()
				.openRawResource(R.raw.start_random_move);
		byte[] buffer = new byte[1024];
		try
		{
			int count = is.read(buffer);
			startRandomMoveJavascript = new String(buffer, 0, count, "utf-8");

		}
		catch (Exception e)
		{
		}
		is = getResources().openRawResource(R.raw.stop_random_move);
		buffer = new byte[1024];
		try
		{
			int count = is.read(buffer);
			stopRandomMoveJavascript = new String(buffer, 0, count, "utf-8");
   
		}
		catch (Exception e)
		{
		}

	}

	public void onClick_Move(View view)
	{
		
		webView.loadDataWithBaseURL(null, startRandomMoveJavascript,
				"text/html", "utf-8", null);

		 
	}

	public void onClick_StopMove(View view)
	{
		webView.loadDataWithBaseURL(null, stopRandomMoveJavascript,
				"text/html", "utf-8", null);

	}

}