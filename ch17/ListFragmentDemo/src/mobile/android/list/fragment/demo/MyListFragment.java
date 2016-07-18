package mobile.android.list.fragment.demo;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyListFragment extends ListFragment
{
	private String[] data = new String[]
	{ "变形金刚3", "狄仁杰传奇", "星际之门", "星际迷航" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.my_list_fragment, container);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, data));
	}

}
