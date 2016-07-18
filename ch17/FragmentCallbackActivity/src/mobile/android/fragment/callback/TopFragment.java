package mobile.android.fragment.callback;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class TopFragment extends Fragment implements OnClickListener
{
	private OnTopButtonClickedListener listener;
	public interface OnTopButtonClickedListener
	{
		
		public void onClick(String name);
	}

	@Override
	public void onAttach(Activity activity)
	{
		if(getActivity() instanceof OnTopButtonClickedListener)
		{
			listener = (OnTopButtonClickedListener)getActivity();
		}
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.top_fragment, null);
		view.setOnClickListener(this);
		return view;
	}
	
	public void onClick(View view)
	{
		if(listener != null)
		{
			listener.onClick("Top Fragment Demo");
		}
	}

}
