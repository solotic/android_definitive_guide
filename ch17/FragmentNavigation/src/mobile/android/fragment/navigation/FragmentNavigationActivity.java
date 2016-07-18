package mobile.android.fragment.navigation;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class FragmentNavigationActivity extends Activity implements
		OnBackStackChangedListener
{

	private void nextFragment(boolean backStackFlag)
	{
		try
		{
			FragmentManager fragmentManager = getFragmentManager();

			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			FragmentPage fragment = new FragmentPage();
			fragmentTransaction.add(R.id.fragment_container, fragment);

			if (backStackFlag)
				// fragmentTransaction.addToBackStack(null);
				//  Ϊÿһ��Fragment״ָ̬��һ��name������Fragment��ǰ����Ҫ
				fragmentTransaction
						.addToBackStack(String.valueOf(getFragmentManager()
								.getBackStackEntryCount() + 1));
			fragmentTransaction.commit();
			fragmentManager.addOnBackStackChangedListener(this);

		}
		catch (Exception e)
		{

		}

	}

	@Override
	public void onBackStackChanged()
	{

		setTitle("��ǰ��" + (getFragmentManager().getBackStackEntryCount() + 1)
				+ "ҳ");

	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_navigation);
		nextFragment(false);
		onBackStackChanged();
	}

	public void onClick_NextPage(View view)
	{
		nextFragment(true);

	}

	public void onClick_PrevPage(View view)
	{
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.popBackStack();
		//  ������ջ��Fragment״̬ȫ����ջ���ָ�����1ҳ
		//fragmentManager.popBackStackImmediate("1",FragmentManager.POP_BACK_STACK_INCLUSIVE);

	}
}