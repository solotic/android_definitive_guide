package mobile.android.install.component;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class InstallComponentActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_install_component);
	}
    public void onClick_QueryWithName(View view)
    {
		Uri uri = Uri.parse("market://search?q=ÐÂÀËÎ¢²©");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
    }
    public void onClick_QueryWithId(View view)
    {
		Uri uri =Uri.parse("market://details?id=com.sina.weibo");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
    }
    public void onClick_InstallAPK(View view)
    {
    	Intent intent = new Intent();
    	intent.setDataAndType(Uri.parse("file:///sdcard/test.apk"),"application/vnd.android.package-archive");  
    	startActivity(intent); 

    }
}
