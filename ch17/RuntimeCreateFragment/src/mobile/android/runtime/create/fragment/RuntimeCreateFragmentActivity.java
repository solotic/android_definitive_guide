package mobile.android.runtime.create.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RuntimeCreateFragmentActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_runtime_create_fragment);
		
	}

	public void onClick_CreateUIFragment(View view)
	{
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		UIFragment fragment = new UIFragment();
		fragmentTransaction.add(R.id.fragment_container, fragment,
				"ui_fragment");
		fragmentTransaction.commit();
	}
}
