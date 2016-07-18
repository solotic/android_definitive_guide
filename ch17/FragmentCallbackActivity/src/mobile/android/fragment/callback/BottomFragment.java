package mobile.android.fragment.callback;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class BottomFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater
				.inflate(R.layout.bottom_fragment, container, false);
		
		return view;
	}
	   
	public void updateText(String value)
	{
		EditText editText = (EditText)getView();
		editText.setText(value);
	}

}
