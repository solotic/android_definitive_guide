package mobile.android.fragment.persistence;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FragmentPersistenceActivity extends Activity
{
	private MyFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_persistence);
		FragmentManager fragmentManager = getFragmentManager();
		if (savedInstanceState != null)
			fragment = (MyFragment) fragmentManager.getFragment(
					savedInstanceState, "fragment");
		if (fragment == null)
		{
			fragment = (MyFragment) getFragmentManager().findFragmentById(
					R.id.fragment_persistence);
		}
	}

	public void onClick_SetFragmentField(View view)
	{
		if (fragment != null)
		{
			fragment.name = "name：新的字段值";
			fragment.getArguments().putString("name", "arg：新设置的值");
			Toast.makeText(this, "成功为name字段赋值", Toast.LENGTH_LONG).show();
		}
	}

	public void onClick_GetFragmentFieldValue(View view)
	{

		if (fragment != null)
		{
			Toast.makeText(this, fragment.name, Toast.LENGTH_LONG).show();
			Toast.makeText(this, fragment.getArguments().getString("name"),
					Toast.LENGTH_LONG).show();
		}
	}


	@Override
	protected void onSaveInstanceState(Bundle outState)
	{

		if (fragment != null)
		{
			getFragmentManager().putFragment(outState, "fragment", fragment);
		}

		super.onSaveInstanceState(outState);
	}

}
