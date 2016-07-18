package mobile.android.capture.screen;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class CaptureScreenActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture_screen);
	}

	public void onClick_CaptureScreen(View view)
	{
		new Handler().postDelayed( new Runnable()
		{
			public void run()
			{
				View v = getWindow().getDecorView();
				v.setDrawingCacheEnabled(true);
				v.buildDrawingCache();
				Bitmap srcBitmap = v.getDrawingCache();
				
				Rect frame = new Rect(); 
				getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
				int statusBarHeight = frame.top; 
				
				Point outSize = new Point();
				getWindowManager().getDefaultDisplay().getSize(outSize);
				int width = outSize.x;
				int height = outSize.y;
				 
				Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, statusBarHeight, width, height - statusBarHeight);
				v.destroyDrawingCache();
			
				
				FileOutputStream fos = null;
				try
				{
					File file = File.createTempFile("capture", ".jpg", new File(
							"/sdcard"));
					fos = new FileOutputStream(file);
					if (null != fos)
					{
						bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
						fos.flush();
						Toast.makeText(CaptureScreenActivity.this, "已成功截屏，截屏文件名：" + file.getName(),
								Toast.LENGTH_LONG).show();

					}
					fos.close();
				}
				catch (Exception e)
				{
				}

			}
		}, 2000);
		
	}

}
