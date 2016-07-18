package mobile.android.apk.client;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import dalvik.system.DexClassLoader;

public class APKClientActivity extends Activity
{
	private DexClassLoader mDexClassLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apkclient);
		String optimizedDirectory = Environment.getDataDirectory().toString()
				+ "/data/" + getPackageName();
		mDexClassLoader = new DexClassLoader("/sdcard/APKLibrary.apk",
				optimizedDirectory, null, ClassLoader.getSystemClassLoader());

	}

	public void onClick_InvokeGetName(View view)
	{
		try
		{
			Class c = mDexClassLoader
					.loadClass("mobile.android.apk.library.MyClass");
			Object obj = c.newInstance();
			Method method = obj.getClass().getMethod("getName", null);
			String name = String.valueOf(method.invoke(obj, null));
			Toast.makeText(this, name, Toast.LENGTH_LONG).show();

		}
		catch (Exception e)
		{
			Toast.makeText(this, "error:" + e.getMessage(), Toast.LENGTH_LONG)
					.show();

		}
	}

}
