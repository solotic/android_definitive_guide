package mobile.android.src.filter;

import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class AndroidSrcFilterActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_src_filter);
	}

	public void onClick_Calculator(View view)
	{

		Intent intent = new Intent("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.APP_CALCULATOR");
		startActivity(intent);

	}

	public void onClick_Browser(View view)
	{

		Intent intent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://blog.csdn.net/nokiaguy"));
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		startActivity(intent);

	}
	public void onClick_Launcher2(View view)
	{

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		PackageManager pm = getPackageManager();
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		for(ResolveInfo resolveInfo: resolveInfos)
		{
			Log.d("name",resolveInfo.activityInfo.name);
		}

	}
}
