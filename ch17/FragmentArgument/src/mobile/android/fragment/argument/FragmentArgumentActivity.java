package mobile.android.fragment.argument;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentArgumentActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_argument);
	}

	public void onClick_SendData(View view)
	{
		MyFragment fragment = new MyFragment();

		Bundle bundle = new Bundle();

		bundle.putString("name", "Hello Fragment1");
		fragment.setArguments(bundle);
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.fragment_container1, fragment, "fragment");

		fragmentTransaction.commit();

		Toast.makeText(this, "数据已成功传递.", Toast.LENGTH_LONG).show();

	}

	public void onClick_ShowArgument(View view)
	{
		EditText editText = (EditText) findViewById(R.id.edittext);

		String name = getFragmentManager().findFragmentByTag("fragment")
				.getArguments().getString("name");
		editText.setText(name);
	}
}
