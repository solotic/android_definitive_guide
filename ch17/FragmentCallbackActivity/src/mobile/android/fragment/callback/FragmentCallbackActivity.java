package mobile.android.fragment.callback;

import mobile.android.fragment.callback.TopFragment.OnTopButtonClickedListener;
import android.app.Activity;
import android.os.Bundle;

public class FragmentCallbackActivity extends Activity implements
		OnTopButtonClickedListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_callback);
	}

	@Override
	public void onClick(String name)
	{
		BottomFragment fragment = (BottomFragment) getFragmentManager()
				.findFragmentByTag("bottom_fragment");
		fragment.updateText("onClick:" + name);

	}

}
