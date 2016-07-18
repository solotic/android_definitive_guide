package mobile.android.transmit.data;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TransmitDataActivity extends Activity implements OnClickListener
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);

	}



	@Override
	public void onClick(View view)
	{
		Intent intent = null;
		switch (view.getId())
		{
			case R.id.button1:

				intent = new Intent(this, MyActivity1.class);
				intent.putExtra("intent_string", "通过Intent传递的字符串");
				intent.putExtra("intent_integer", 300);
				Data data = new Data();
				data.id = 1000;
				data.name = "Android";
				intent.putExtra("intent_object", data);
				startActivity(intent);

				break;
			case R.id.button2:
				intent = new Intent(this, MyActivity2.class);
				MyActivity2.id = 3000;
				MyActivity2.name = "保时捷";
				MyActivity2.data = new Data();
				MyActivity2.data.id = 1000;
				MyActivity2.data.name = "Android";
				startActivity(intent);
				break;
			case R.id.button3:
				intent = new Intent(this, MyActivity3.class);
				ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		
				Data clipboardData = new Data();
				clipboardData.id = 6666;
				clipboardData.name = "通过Clipboard传递的数据";
				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				String base64Str = "";
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					oos.writeObject(clipboardData);

					base64Str = Base64.encodeToString(baos.toByteArray(),
							Base64.DEFAULT);
					oos.close();
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
			
				ClipData clipData = ClipData.newPlainText("data", base64Str);
		        clipboard.setPrimaryClip(clipData);
				
				startActivity(intent);
				break;
			case R.id.button4:
				MyApp myApp = (MyApp) getApplicationContext();
				myApp.country = "美国";
				myApp.data.id = 1234;
				myApp.data.name = "飞碟";
				intent = new Intent(this, MyActivity4.class);
				startActivity(intent);
				break;

		}

	}

}