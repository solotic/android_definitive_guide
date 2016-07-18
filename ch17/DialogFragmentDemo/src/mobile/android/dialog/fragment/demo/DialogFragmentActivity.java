package mobile.android.dialog.fragment.demo;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

public class DialogFragmentActivity extends Activity
{


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_fragment);
		
	} 

	public void onClick_BasicDialogFragment(View view)
	{
		BasicDialogFragment basicDialogFragment = new BasicDialogFragment(
				DialogFragment.STYLE_NORMAL, 0);
		basicDialogFragment.show(getFragmentManager(), null);
	}
	public void onClick_AlertDialogFragment(View view)
	{
		AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
		alertDialogFragment.show(getFragmentManager(), "alert");

		
	}
	public void close()
	{
	
		Fragment fragment = getFragmentManager().findFragmentByTag("alert");
		if(fragment != null)
		{
			getFragmentManager().beginTransaction().remove(fragment);
		}
	}
}
