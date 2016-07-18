package mobile.android.async.task.demo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends Activity
{
	private ProgressBar mProgressBar;
	private TextView mTextView;
	private ProgressAsyncTask mProgressAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		mTextView = (TextView) findViewById(R.id.textView1);
	}

	private class ProgressAsyncTask extends AsyncTask<String, Integer, Integer>
	{
        private int mCount;
		@Override
		protected Integer doInBackground(String... params)
		{
			mProgressBar.setMax(params.length);
			mCount = params.length;
			
			for (int i = 0; i < mCount; i++)
			{
				publishProgress(i + 1);
				if (isCancelled())
					break;
				try
				{
					Thread.sleep(2000);
				}
				catch (Exception e)
				{

				}
			}

			return params.length;
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			super.onPostExecute(result);
			Toast.makeText(AsyncTaskActivity.this, "任务完成，共处理" + result + "个值.", Toast.LENGTH_LONG)
					.show();

		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			super.onProgressUpdate(values);
			mProgressBar.setProgress(values[0]);
			mTextView.setText(100 * values[0]/mCount + "%");
		}

		@Override
		protected void onCancelled()
		{
			Toast.makeText(AsyncTaskActivity.this, "任务已取消", Toast.LENGTH_LONG)
					.show();

			super.onCancelled();
		}

	}

	public void onClick_Start(View view)
	{
		mProgressAsyncTask = new ProgressAsyncTask();

		mProgressAsyncTask.execute("str1", "str2", "str3", "str4", "str5");
	}

	public void onClick_Cancel(View view)
	{
		mProgressAsyncTask.cancel(true);
	}
}
