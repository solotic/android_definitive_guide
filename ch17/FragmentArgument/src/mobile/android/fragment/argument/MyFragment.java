package mobile.android.fragment.argument;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view =  inflater.inflate(R.layout.my_fragment, container,
				false);
		
		return view;
	}

	@Override
	public void onDestroyView()
	{
		
		Log.d("name", getArguments().getString("name"));
		super.onDestroyView();
	}  

}
