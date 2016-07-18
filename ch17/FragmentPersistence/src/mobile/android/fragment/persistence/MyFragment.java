package mobile.android.fragment.persistence;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment
{
	public String name = "name£ºdefault";

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		if (savedInstanceState != null)
		{
			String s = savedInstanceState.getString("name");
			if (s != null)
				name = s;

		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		outState.putString("name", name);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState)
	{
		if (getArguments() == null)
		{
			Bundle bundle = new Bundle();
			bundle.putString("name", "arg:default");
			setArguments(bundle);
		}
		super.onInflate(activity, attrs, savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.my_fragment, container, false);
		return view;
	}

}
