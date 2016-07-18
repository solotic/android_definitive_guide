package mobile.android.http.get.post;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener
{

	@Override
	public void onClick(View view)
	{
		// 读者需要将本例中的IP换成自己机器的IP
		String url = "http://192.168.17.156:8080/querybooks/QueryServlet";
		TextView tvQueryResult = (TextView) findViewById(R.id.tvQueryResult);
		EditText etBookName = (EditText) findViewById(R.id.etBookName);
		HttpResponse httpResponse = null;
		try
		{
			switch (view.getId())
			{
			// 提交HTTP GET请求
				case R.id.btnGetQuery:
					// 向url添加请求参数
					url += "?bookname=" + etBookName.getText().toString();
					// 第1步：创建HttpGet对象
					HttpGet httpGet = new HttpGet(url);
					// 第2步：使用execute方法发送HTTP GET请求，并返回HttpResponse对象
					httpResponse = new DefaultHttpClient().execute(httpGet);
					// 判断请求响应状态码，状态码为200表示服务端成功响应了客户端的请求
					if (httpResponse.getStatusLine().getStatusCode() == 200)
					{
						// 第3步：使用getEntity方法获得返回结果
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						// 去掉返回结果中的“\r”字符，否则会在结果字符串后面显示一个小方格
						tvQueryResult.setText(result.replaceAll("\r", ""));
					}
					break;
				// 提交HTTP POST请求
				case R.id.btnPostQuery:
					// 第1步：创建HttpPost对象
					HttpPost httpPost = new HttpPost(url);
					// 设置HTTP POST请求参数必须用NameValuePair对象
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("bookname", etBookName
							.getText().toString()));
					// 设置HTTP POST请求参数
					httpPost.setEntity(new UrlEncodedFormEntity(params,
							HTTP.UTF_8));
					// 第2步：使用execute方法发送HTTP POST请求，并返回HttpResponse对象
					httpResponse = new DefaultHttpClient().execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200)
					{
						// 第3步：使用getEntity方法获得返回结果
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						// 去掉返回结果中的“\r”字符，否则会在结果字符串后面显示一个小方格
						tvQueryResult.setText(result.replaceAll("\r", ""));
					}
					break;
			}
		}
		catch (Exception e)
		{
			tvQueryResult.setText(e.getMessage());
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnGetQuery = (Button) findViewById(R.id.btnGetQuery);
		Button btnPostQuery = (Button) findViewById(R.id.btnPostQuery);
		btnGetQuery.setOnClickListener(this);
		btnPostQuery.setOnClickListener(this);

	}
}