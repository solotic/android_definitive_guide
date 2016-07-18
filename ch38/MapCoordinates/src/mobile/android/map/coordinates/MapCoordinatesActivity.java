package mobile.android.map.coordinates;

import java.io.InputStream;


import android.app.Activity; 
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapCoordinatesActivity extends Activity
{
    private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icon_flag_map);
		try  
		{
			InputStream is = getResources().getAssets().open("map_coordinates.html");
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
