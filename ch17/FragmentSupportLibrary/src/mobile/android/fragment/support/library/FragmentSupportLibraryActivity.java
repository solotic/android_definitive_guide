package mobile.android.fragment.support.library;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FragmentSupportLibraryActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_support_library);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		MyFragment myFragment = new MyFragment();

		transaction.add(R.id.container, myFragment);
		
		transaction.commit();
	}

}
