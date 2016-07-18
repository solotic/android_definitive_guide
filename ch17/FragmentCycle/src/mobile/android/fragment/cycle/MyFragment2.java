package mobile.android.fragment.cycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment2 extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		Log.d("Fragment2", "onCreateView");
		View view = inflater.inflate(R.layout.my_fragment2, null);
		return view;
	}

	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState)
	{
		Log.d("Fragment2", "onInflate");
		Log.d("Fragment2_class", attrs.getAttributeValue(null, "class"));
		super.onInflate(activity, attrs, savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity)
	{
		Log.d("Fragment2", "onAttach");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.d("Fragment2", "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		Log.d("Fragment2", "onViewCreated");
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		Log.d("Fragment2", "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart()
	{
		Log.d("Fragment2", "onStart");
		super.onStart();
	}

	@Override
	public void onResume()
	{
		Log.d("Fragment2", "onResume");
		super.onResume();
	}

	@Override
	public void onPause()
	{
		Log.d("Fragment2", "onPause");
		super.onPause();
	}

	@Override
	public void onStop()
	{
		Log.d("Fragment2", "onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView()
	{
		Log.d("Fragment2", "onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy()
	{
		Log.d("Fragment2", "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach()
	{
		Log.d("Fragment2", "onDetach");
		super.onDetach();
	}

}
