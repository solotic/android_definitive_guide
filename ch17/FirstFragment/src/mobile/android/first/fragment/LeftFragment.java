package mobile.android.first.fragment;

import java.io.InputStream;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftFragment extends Fragment implements OnItemClickListener
{

	private String[] data = new String[]
	{ "灵魂战车2", "变形金刚3:月黑之时", "敢死队2" };
	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.left_fragment, null);
		listView = (ListView) view.findViewById(R.id.listview_movie_list);
		listView.setOnItemClickListener(this);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_activated_1,
				data);
		listView.setAdapter(arrayAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		return view;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		try
		{

			TextView textView = (TextView) getActivity().findViewById(
					R.id.textview_detail);
			InputStream is = getActivity().getResources().getAssets()
					.open("m" + position);
			byte[] buffer = new byte[1024];
			int count = is.read(buffer);
			String detail = new String(buffer, 0, count, "utf-8");
			if (textView == null)
			{
				Intent intent = new Intent(getActivity(), DetailActivity.class);
				intent.putExtra("detail", detail);
				startActivity(intent);
			}
			else
			{
				textView.setText(detail);
			}
			is.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

}
