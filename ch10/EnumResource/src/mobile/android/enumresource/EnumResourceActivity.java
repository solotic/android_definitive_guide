package mobile.android.enumresource;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EnumResourceActivity extends Activity
{
	private TextView mResourceId;
	private EditText mResourceName;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enum_resource);
		mResourceId = (TextView) findViewById(R.id.textview_resource_id);
		mResourceName = (EditText) findViewById(R.id.edittext_resource_name);

	}

	private int getResourceId(String name)
	{
		Class[] resourceClasses = R.class.getClasses();

		for (Class resourceClass : resourceClasses)
		{

			try
			{
				Field field = resourceClass.getField(name);
				int resourceID = field.getInt(null);
				return resourceID;
			}
			catch (Exception e)
			{

			}
		}
		return -1;
	}

	public void onClick_ResourceId(View view)
    {
    	String resourceName = String.valueOf(mResourceName.getText());
    	int resourceID = getResourceId(resourceName);
		if(resourceID == -1)
			mResourceId.setText("资源不存在");
		else
		    mResourceId.setText("0x" + Integer.toHexString(resourceID));
    }
}
