package mobile.android.custom.action.provider;


import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class NewCustomActionProvider extends ActionProvider
{
    private Context mContext;
	public NewCustomActionProvider(Context context)
	{
		super(context);
		mContext = context;
	}

	@Override
	public View onCreateActionView()
	{
		LayoutInflater inflater =  LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.new_custom_action_provider, null);
	    ImageButton imageButton = (ImageButton)view.findViewById(R.id.imagebutton);
	    imageButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Toast.makeText(mContext, "点击Action按钮", Toast.LENGTH_LONG).show();
				
			}
		});
	    		
		return view;
	}

	@Override
	public boolean onPerformDefaultAction()
	{
	
		Toast.makeText(mContext, "点击菜单项", Toast.LENGTH_LONG).show();
		return true;
	}


}
