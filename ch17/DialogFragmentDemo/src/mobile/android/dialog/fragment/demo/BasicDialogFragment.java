package mobile.android.dialog.fragment.demo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BasicDialogFragment extends DialogFragment
{
	private int mStyle;
	private int mTheme;

	public BasicDialogFragment(int style, int theme)
	{
		mStyle = style;
		mTheme = theme;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setStyle(mStyle, mTheme);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.basic_dialog_fragment, container,
				false);
		View text = view.findViewById(R.id.text);
		((TextView) text).setText("BasicDialogFragment");


		Button button = (Button) view.findViewById(R.id.show);
		button.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				
				dismiss();
			}
		});

		return view;
	}
}