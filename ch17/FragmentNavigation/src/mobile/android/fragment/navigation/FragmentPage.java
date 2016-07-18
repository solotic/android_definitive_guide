package mobile.android.fragment.navigation;

import java.util.Random;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentPage extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_page, container, false);

		EditText editText = (EditText)view.findViewById(R.id.edittext);

		editText.setText(String.valueOf(Math.abs(new Random().nextLong())));
		return view;
	}


}
