package mobile.android.click.map;

import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ClickMapActivity extends Activity
{
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_map);

		try
		{
			InputStream is = getResources().getAssets().open("click_map.html");
			byte[] buffer = new byte[20000];
			int count = is.read(buffer);
			String mapStr = new String(buffer, 0, count, "utf-8");
			is.close();

			mWebView = (WebView) findViewById(R.id.webview);
			mWebView.getSettings().setJavaScriptEnabled(true);

			mWebView.setWebViewClient(new WebViewClient());
			mWebView.addJavascriptInterface(new Object()
			{
				public void showDialog(String title, float latitude, float longitude)
				{
					new AlertDialog.Builder(ClickMapActivity.this)
							.setTitle(title).setIcon(R.drawable.ic_launcher)
							.setMessage("Î³¶È:" + latitude + "\n¾­¶È:" + longitude)
							.show();
				}  

			}, "android");

			mWebView.loadDataWithBaseURL(null, mapStr, "text/html", "utf-8",
					null);
		}
		catch (Exception e)
		{

		}
	}

}
