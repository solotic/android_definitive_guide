package mobile.android.filter.consumer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class FilterConsumerActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter_consumer);

	}

	public void onClick_IntentFilter2(View view)
	{
		Intent intent = new Intent("android.intent.action.MYACTION1");
		intent.addCategory("android.intent.category.MYCATEGORY1");
		intent.addCategory("android.intent.category.MYCATEGORY2");
		startActivity(intent);
	}
	
	public void onClick_IntentFilter3(View view)
	{
		Intent intent = new Intent("android.intent.action.MYACTION4");

		startActivity(intent);
	}
	public void onClick_IntentFilter4(View view)  
	{
		Intent intent = new Intent("android.intent.action.MYACTION5");
		intent.setDataAndType(Uri.parse("http://www.microsoft.com:8888/index.html"), "audio/mp3");
		startActivity(intent);   
	}      
	public void onClick_IntentFilter5(View view)  
	{
		Intent intent = new Intent("android.intent.action.MYACTION6");
		intent.setDataAndType(Uri.parse("http://www.google.com:8080/work/upload.jsp"), "audio/*");
		startActivity(intent);   
	}     	
	public void onClick_IntentFilter6(View view)  
	{
		Intent intent = new Intent("android.intent.action.MYACTION7");
		intent.setDataAndType(Uri.parse("ftp://192.168.17.100:8080/work/upload.html"), "audio/*");
		startActivity(intent);   
	}  
	public void onClick_IntentFilter7(View view)  
	{
		Intent intent = new Intent("android.intent.action.MYACTION7");
		//intent.setDataAndType(Uri.parse("https://192.168.17.111:8888/work/test.up"), "audio/wav");
		//intent.setDataAndType(Uri.parse("https://192.168.17.111:8888/test/up.aspx"), "audio/wav");
		intent.setDataAndType(Uri.parse("https://192.168.17.111:8888/p/m/abc.html"), "audio/wav");
		startActivity(intent);   
	}  

}  
