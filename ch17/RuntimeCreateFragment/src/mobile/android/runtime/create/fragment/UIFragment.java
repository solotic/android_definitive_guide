package mobile.android.runtime.create.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

public class UIFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		container.setLayoutParams(new LayoutParams(200, 200));
		View view = inflater.inflate(R.layout.ui_fragment, container, false);
		return view;
	}

}
