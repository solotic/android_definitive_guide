package mobile.android.contact.async.loader;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleCursorAdapter;

public class ContactFragment extends ListFragment implements
		OnQueryTextListener, LoaderManager.LoaderCallbacks<Cursor>
{
	private SimpleCursorAdapter mAdapter;
	private String mCurFilter;
	static final String[] CONTACTS_SUMMARY_PROJECTION = new String[]
	{ Contacts._ID, Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS,
			Contacts.CONTACT_PRESENCE, Contacts.PHOTO_ID, Contacts.LOOKUP_KEY, };

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		setEmptyText("没有改联系人");

		setHasOptionsMenu(true);

		mAdapter = new SimpleCursorAdapter(getActivity(),
				android.R.layout.simple_list_item_2, null, new String[]
				{ ContactsContract.Contacts.DISPLAY_NAME,
						ContactsContract.Contacts.CONTACT_STATUS }, new int[]
				{ android.R.id.text1, android.R.id.text2 }, 0);
		setListAdapter(mAdapter);

		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{

		MenuItem item = menu.add("搜索");
		item.setIcon(R.drawable.search);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		SearchView sv = new SearchView(getActivity());

		sv.setOnQueryTextListener(this);
		item.setActionView(sv);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args)
	{
		Uri baseUri;
		if (mCurFilter != null)
		{
			baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI,
					Uri.encode(mCurFilter));
		}
		else
		{
			baseUri = Contacts.CONTENT_URI;
		}

		String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
				+ Contacts.HAS_PHONE_NUMBER + "=1) AND ("
				+ Contacts.DISPLAY_NAME + " != '' ))";
		return new CursorLoader(getActivity(), baseUri,
				CONTACTS_SUMMARY_PROJECTION, select, null,
				Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data)
	{
		mAdapter.swapCursor(data);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader)
	{

		mAdapter.swapCursor(null);
	}
  
	@Override
	public boolean onQueryTextSubmit(String query)
	{

		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText)
	{
		mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
		getLoaderManager().restartLoader(0, null, this);
		return true;
	}

}
