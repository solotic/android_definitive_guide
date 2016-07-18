package mobile.android.dialog.fragment.demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class AlertDialogFragment extends DialogFragment
{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		

		return new AlertDialog.Builder(getActivity())
				.setIcon(R.drawable.ic_launcher)
				.setTitle("我的对话框")
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int whichButton)
							{
								Log.d("ok_clicked", "ok");
								((DialogFragmentActivity)getActivity()).close();
							}
						})
				.setNegativeButton(android.R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int whichButton)
							{
								Log.d("cancel_clicked", "cancel");
								dismiss();
							}
						}).create();
	}
}