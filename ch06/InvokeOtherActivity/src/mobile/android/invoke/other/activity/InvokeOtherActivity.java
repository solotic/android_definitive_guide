package mobile.android.invoke.other.activity;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class InvokeOtherActivity extends Activity
{
    private Context mContext;
    private Class mClass;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invoke_other);
		try
		{
			mContext = createPackageContext(
					"mobile.android.web.browser", Context.CONTEXT_INCLUDE_CODE
							| Context.CONTEXT_IGNORE_SECURITY);
			mClass = mContext.getClassLoader().loadClass(
					"mobile.android.web.browser.WebBrowserActivity");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void onClick_Action(View view)
	{
		Intent webIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://blog.csdn.net/nokiaguy"));
		startActivity(webIntent);
	
	}

	public void onClick_SetClassName(View view)
	{
		Intent webIntent = new Intent();
		webIntent.setClassName("mobile.android.web.browser",
				"mobile.android.web.browser.WebBrowserActivity");
		webIntent.setData(Uri.parse("http://nokiaguy.blogjava.net"));
		startActivity(webIntent);
	}

	public void onClick_SetClassName_Context(View view)
	{
		if(mContext == null || mClass == null) return;
		Intent webIntent = new Intent();

		webIntent.setClassName(mContext,
				"mobile.android.web.browser.WebBrowserActivity");
		webIntent.setData(Uri.parse("http://nokiaguy.cnblogs.com"));
		startActivity(webIntent);

	}

	public void onClick_SetClass(View view)
	{
		if(mContext == null || mClass == null) return;
		Intent webIntent = new Intent();
		webIntent.setClass(mContext, mClass);
		webIntent.setData(Uri.parse("http://nokiaguy.cnblogs.com"));
		startActivity(webIntent);		
	}
	
	public void onClick_SetComponentName(View view)
	{
		if(mContext == null || mClass == null) return;
		ComponentName cn = new ComponentName(mContext, mClass);
		Intent webIntent = new Intent();
		webIntent.setComponent(cn);
		webIntent.setData(Uri.parse("http://nokiaguy.cnblogs.com"));
		startActivity(webIntent);
	}
	public void onClick_ShowCalculator(View view)
	{
		Intent intent = new Intent();
		intent.setClassName("com.android.calculator2",
				"com.android.calculator2.Calculator");
		startActivity(intent);
	}
}
