package mobile.android.mms.browser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MMSBrowser extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ScrollView scrollView = (ScrollView) getLayoutInflater().inflate(
				R.layout.main, null);
		LinearLayout linearLayout = (LinearLayout) scrollView
				.findViewById(R.id.linearlayout);
		String mid = getIntent().getStringExtra("mid");
		Cursor cursor = getContentResolver().query(
				Uri.parse("content://mms/part"), null, "mid=?", new String[]
				{ mid }, "_id asc");
		while (cursor.moveToNext())
		{

			String type = cursor.getString(cursor.getColumnIndex("ct"));
			String text = cursor.getString(cursor.getColumnIndex("text"));
			if (text != null)
				text = text.replaceAll("\\r", "");
			byte[] data = null;

			Uri partUri = Uri.parse("content://mms/part/"
					+ cursor.getString(cursor.getColumnIndex("_id")));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = null;

			try
			{
				is = getContentResolver().openInputStream(partUri);
				byte[] buffer = new byte[8192];
				int len = 0;
				while ((len = is.read(buffer)) >= 0)
				{
					baos.write(buffer, 0, len);
				}

			}
			catch (IOException e)
			{
			}
			finally
			{
				if (is != null)
				{
					try
					{
						is.close();
						data = baos.toByteArray();
					}
					catch (IOException e)
					{

					}
				}
			}
			if (type.toLowerCase().contains("text"))
			{
				TextView textView = (TextView) getLayoutInflater().inflate(
						R.layout.text, null);
				textView.setText(text);
				linearLayout.addView(textView);
			}
			else if (type.toLowerCase().contains("image"))
			{
				ImageView imageView = (ImageView) getLayoutInflater().inflate(
						R.layout.image, null);
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				imageView.setImageBitmap(bitmap);
				linearLayout.addView(imageView);
			}
			else if (type.toLowerCase().contains("video"))
			{

				String filename = "/sdcard/temp.3gp";
				ImageView imageView = (ImageView) getLayoutInflater().inflate(
						R.layout.image, null);
				if (!new File(filename).exists())
				{
					try
					{

						FileOutputStream fos = new FileOutputStream(filename);
						fos.write(data);
						fos.close();
					}
					catch (Exception e)
					{
					}
				}
				Bitmap bitmap = getVideoThumbnail(filename);
				imageView.setImageBitmap(bitmap);
				linearLayout.addView(imageView);
			}
		}

		setContentView(scrollView);
	}

	public Bitmap getVideoThumbnail(String filename)
	{
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inDither = false;
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;

		String whereClause = MediaStore.Video.Media.DATA + " = '" + filename
				+ "'";

		Cursor cursor = getContentResolver().query(
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]
				{ MediaStore.Video.Media._ID }, whereClause, null, null);
		boolean delete = false;
		if (cursor == null || cursor.getCount() == 0)
		{
			ContentValues values = new ContentValues();
			values.put(MediaStore.Video.Media.DATA, filename);
			getContentResolver().insert(
					MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
			cursor = getContentResolver().query(
					MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]
					{ MediaStore.Video.Media._ID }, whereClause, null, null);
			if (cursor == null || cursor.getCount() == 0)
				return null;
			delete = true;
		}
		cursor.moveToFirst();

		String videoId = cursor.getString(cursor
				.getColumnIndex(MediaStore.Video.Media._ID));

		if (videoId == null)
		{
			return null;
		}
		cursor.close();
		long videoIdLong = Long.parseLong(videoId);
		bitmap = MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(),
				videoIdLong, Images.Thumbnails.MICRO_KIND, options);
		if (delete)
		{
			getContentResolver().delete(
					MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
					MediaStore.Video.Media.DATA + "=?", new String[]
					{ filename });
		}
		return bitmap;
	}

}
