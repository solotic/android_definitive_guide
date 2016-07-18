package mobile.android.invoke.sina.weibo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class InvokeSinaWeiboActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invoke_sina_weibo);
	}
    public void onClick_StartSinaWeibo(View view)
    {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.BROWSABLE");
		intent.setData(Uri.parse("sinaweibo://splash"));
		startActivity(intent);
    }
    public void onClick_PostWeibo(View view)
    {
		Intent intent = new Intent("com.sina.weibo.intent.action.NEW_BLOG");
		startActivity(intent);
    }
    public void onClick_Share(View view)
    {
    	
		Intent intent = new Intent("android.intent.action.SEND");
		intent.putExtra(Intent.EXTRA_TEXT, "今天气温很低，注意保暖哦，亲！");
		intent.setType("text/plain");
		startActivity(intent);
    }
    public void onClick_ShareWithSinaWeibo(View view)
    {
		Intent intent = new Intent("android.intent.action.SEND");
		intent.setClassName("com.sina.weibo", "com.sina.weibo.EditActivity");
		intent.putExtra(Intent.EXTRA_TEXT, "今天气温很低，注意保暖哦，亲！");
		intent.setType("text/plain");
		startActivity(intent);
    }
}
