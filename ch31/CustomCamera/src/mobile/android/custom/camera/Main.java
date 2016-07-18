package mobile.android.custom.camera;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends Activity implements OnClickListener
{
	private ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
		btnTakePicture.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageview);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == 1)
		{
			if (resultCode == 20)
			{

				File captureFile = new File("/sdcard/camera.jpg");
				try
				{
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(captureFile));

					CameraPreview.cameraBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
					bos.flush();
					bos.close();

					imageView.setImageBitmap(CameraPreview.cameraBitmap);
					setTitle("opk");
				}
				catch (Exception e)
				{
					
				}
			}

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View view)
	{
		Intent intent = new Intent(this, CameraPreview.class);
		startActivityForResult(intent, 1);
		

	}

}
