package mobile.android.actionview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class ActionViewActivity extends Activity implements
		OnActionExpandListener
{
	private Menu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_action_view, menu);
		this.menu = menu;
		for(int i = 0; i < menu.size(); i++)
		{
			menu.getItem(i).setOnActionExpandListener(this);
		}

		return true;
	}

	public void onClick_ExpandSearchView(View view)
	{
		menu.findItem(R.id.menu_item_search).expandActionView();
	}

	public void onClick_CollapseSearchView(View view)
	{
		menu.findItem(R.id.menu_item_search).collapseActionView();
	}
 
	@Override
	public boolean onMenuItemActionExpand(MenuItem item)
	{
		Toast.makeText(this, "<" + item.getTitle() + ">已经展开", Toast.LENGTH_LONG)
				.show();
		
		return true;
	}

	@Override
	public boolean onMenuItemActionCollapse(MenuItem item)
	{
		Toast.makeText(this, "<" + item.getTitle() + ">已经收缩", Toast.LENGTH_LONG)
		.show();
		return true;
	}
   

}
