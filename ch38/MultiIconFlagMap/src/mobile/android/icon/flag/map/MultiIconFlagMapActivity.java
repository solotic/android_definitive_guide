package mobile.android.icon.flag.map;

import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MultiIconFlagMapActivity extends Activity
{
    private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_icon_flag_map);
		
		try  
		{
			InputStream is = getResources().getAssets().open("multi_icon_flag.html");
			byte[] buffer = new byte[20000];
			int count = is.read(buffer);
			String mapStr = new String(buffer, 0, count, "utf-8");
			is.close();
			             
   
			mWebView = (WebView) findViewById(R.id.webview);
			mWebView.getSettings().setJavaScriptEnabled(true);

			mWebView.setWebViewClient(new WebViewClient());
 
  
			mWebView.loadDataWithBaseURL(null, mapStr,
					"text/html", "utf-8", null);
		}
		catch (Exception e)
		{

		}
	}


}
