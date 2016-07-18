package mobile.android.fragment.cycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment1 extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		Log.d("Fragment1", "onCreateView");

		View view = inflater.inflate(R.layout.my_fragment1, null);
		Log.d("Fragment1_onCreateView_getActivity_edittext1",
				String.valueOf(getActivity().findViewById(R.id.edittext1)));
		Log.d("Fragment1_onCreateView_view_edittext1",
				String.valueOf(view.findViewById(R.id.edittext1)));

		return view;
	}

	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState)
	{

		Log.d("Fragment1", "onInflate");
		Log.d("Fragment1_onInflate_activity_stringResource", activity
				.getResources().getString(R.string.app_name));
		Log.d("Fragment1_onInflate_class",
				attrs.getAttributeValue(null, "class"));
		Log.d("Fragment1_onInflate_getActivity", String.valueOf(getActivity()));
		Log.d("Fragment1_onInflate_activity_edittext1",
				String.valueOf(activity.findViewById(R.id.edittext1)));
		Log.d("Fragment1_onInflate_savedInstanceState",
				String.valueOf(savedInstanceState));
		super.onInflate(activity, attrs, savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity)
	{
		Log.d("Fragment1", "onAttach");
		Log.d("Fragment1_onAttach_activity_edittext1",
				String.valueOf(activity.findViewById(R.id.edittext1)));
		Log.d("Fragment1_onAttach_getActivity_edittext1",
				String.valueOf(getActivity().findViewById(R.id.edittext1)));
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.d("Fragment1", "onCreate");
		Log.d("Fragment1_onCreate_getActivity", String.valueOf(getActivity()));
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		Log.d("Fragment1", "onViewCreated");
		Log.d("Fragment1_onViewCreated_getActivity_edittext1",
				String.valueOf(getActivity().findViewById(R.id.edittext1)));
		Log.d("Fragment1_onViewCreated_view_edittext1",
				String.valueOf(view.findViewById(R.id.edittext1)));

		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		Log.d("Fragment1", "onActivityCreated");
		Log.d("Fragment1_onActivityCreated_getActivity_edittext1",
				String.valueOf(getActivity().findViewById(R.id.edittext1)));
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart()
	{
		Log.d("Fragment1", "onStart");
		super.onStart();
	}

	@Override
	public void onResume()
	{
		Log.d("Fragment1", "onResume");
		super.onResume();
	}

	@Override
	public void onPause()
	{
		Log.d("Fragment1", "onPause");
		super.onPause();
	}

	@Override
	public void onStop()
	{
		Log.d("Fragment1", "onStop");
		
		super.onStop();
	}

	@Override
	public void onDestroyView()
	{
		Log.d("Fragment1", "onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy()
	{
		Log.d("Fragment1", "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach()
	{
		Log.d("Fragment1", "onDetach");
		super.onDetach();
	}

}
