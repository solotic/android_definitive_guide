package mobile.android.all.location.providers;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AllProvidersActivity extends Activity
{
	ListView mProviders;
	LocationManager mLocationManager;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mProviders = (ListView) findViewById(R.id.providers);
		// 获取LocationManager对象
		mLocationManager = (LocationManager)getSystemService(
			Context.LOCATION_SERVICE);
		
		// 获取所有的LocationProvider的名称
		List<String> providerNames = mLocationManager.getAllProviders();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			this,
			android.R.layout.simple_list_item_1,
			providerNames);
		// 使用ListView来显示所有可用的LocationProvider
		mProviders.setAdapter(adapter);
	}
}