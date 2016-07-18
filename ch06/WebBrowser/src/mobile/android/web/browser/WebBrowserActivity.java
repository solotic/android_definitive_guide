package mobile.android.web.browser;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebBrowserActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_browser);
		WebView webview = (WebView) findViewById(R.id.webview);
		Uri uri = getIntent().getData();
		if (uri != null)
		{
			webview.loadUrl(uri.toString());
			setTitle(uri.toString());
		}

	}
}
