package mobile.android.dm;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class DMActivity extends Activity
{
	private DownloadManager mDownloadManager;
	private long mDownloadReference;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dm);
		mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		IntentFilter filter = new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		DownloadCompleteReceiver downloadReceiver = new DownloadCompleteReceiver();
		registerReceiver(downloadReceiver, filter);
	}

	public void onClick_DownloadFile(View view)
	{

		Uri uri = Uri.parse("http://img31.ddimg.cn/71/7/23043311-1_w.jpg");
		DownloadManager.Request request = new DownloadManager.Request(uri);
	   // request.setNotificationVisibility(Request.VISIBILITY_HIDDEN);
		request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
				| DownloadManager.Request.NETWORK_MOBILE);

		// 设置下载标题，改标题会在通知中显示  
		request.setTitle("下载图像");
		// 设置下载描述, 该描述会在通知中显示
		request.setDescription("正在使用DownloadManager下载图像.");
		// 指定图像文件下载到本地的目录和文件名

		request.setDestinationInExternalFilesDir(this, "test/images",
				"download_image.jpg");

		// 将下载任务加入队列，并返回下载任务的引用
		mDownloadReference = mDownloadManager.enqueue(request);
		setTitle("正在下载图像...");

	}

	public void onClick_CancelDownload(View view)
	{
		mDownloadManager.remove(mDownloadReference);

	}

	public void onClick_ShowDownloadHistory(View view)
	{
		Intent intent = new Intent();
		intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
		startActivity(intent);

	}

	public void onClick_QueryDownloadStatus(View view)
	{
		Query myDownloadQuery = new Query();
		// 设置查询条件
		myDownloadQuery.setFilterById(mDownloadReference);

		
		Cursor cursor = mDownloadManager.query(myDownloadQuery);
		if (cursor.moveToFirst())
		{
			checkStatus(cursor);
		}
	}

	private void checkStatus(Cursor cursor)
	{

		
		int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
		int status = cursor.getInt(columnIndex);

		int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
		int reason = cursor.getInt(columnReason);
		
		int filenameIndex = cursor
				.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
		String filename = cursor.getString(filenameIndex);

		String statusText = "";
		String reasonText = "";

		switch (status)
		{
			case DownloadManager.STATUS_FAILED:
				statusText = "STATUS_FAILED";
				switch (reason)
				{
					case DownloadManager.ERROR_CANNOT_RESUME:
						reasonText = "ERROR_CANNOT_RESUME";
						break;
					case DownloadManager.ERROR_DEVICE_NOT_FOUND:
						reasonText = "ERROR_DEVICE_NOT_FOUND";
						break;
					case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
						reasonText = "ERROR_FILE_ALREADY_EXISTS";
						break;
					case DownloadManager.ERROR_FILE_ERROR:
						reasonText = "ERROR_FILE_ERROR";
						break;
					case DownloadManager.ERROR_HTTP_DATA_ERROR:
						reasonText = "ERROR_HTTP_DATA_ERROR";
						break;
					case DownloadManager.ERROR_INSUFFICIENT_SPACE:
						reasonText = "ERROR_INSUFFICIENT_SPACE";
						break;
					case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
						reasonText = "ERROR_TOO_MANY_REDIRECTS";
						break;
					case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
						reasonText = "ERROR_UNHANDLED_HTTP_CODE";
						break;
					case DownloadManager.ERROR_UNKNOWN:
						reasonText = "ERROR_UNKNOWN";
						break;
				}
				break;
			case DownloadManager.STATUS_PAUSED:
				statusText = "STATUS_PAUSED";
				switch (reason)
				{
					case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
						reasonText = "PAUSED_QUEUED_FOR_WIFI";
						break;
					case DownloadManager.PAUSED_UNKNOWN:
						reasonText = "PAUSED_UNKNOWN";
						break;
					case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
						reasonText = "PAUSED_WAITING_FOR_NETWORK";
						break;
					case DownloadManager.PAUSED_WAITING_TO_RETRY:
						reasonText = "PAUSED_WAITING_TO_RETRY";
						break;
				}
				break;
			case DownloadManager.STATUS_PENDING:
				statusText = "STATUS_PENDING";
				break;
			case DownloadManager.STATUS_RUNNING:
				statusText = "STATUS_RUNNING";
				break;
			case DownloadManager.STATUS_SUCCESSFUL:
				statusText = "STATUS_SUCCESSFUL";
				reasonText = "Filename:\n" + filename;
				break;
		}

		Toast toast = Toast.makeText(this, statusText + "\n" + reasonText,
				Toast.LENGTH_LONG);

		toast.show();

	}

	private class DownloadCompleteReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent)
		{
			if (intent.getAction().equals(
					DownloadManager.ACTION_DOWNLOAD_COMPLETE))
			{
				long referenceId = intent.getLongExtra(
						DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				if (mDownloadReference == referenceId)
				{
					setTitle("图像文件下载完成.");
				}
			}
		}

	}
}
