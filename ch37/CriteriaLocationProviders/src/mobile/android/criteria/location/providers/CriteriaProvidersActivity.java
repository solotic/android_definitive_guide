package mobile.android.criteria.location.providers;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CriteriaProvidersActivity extends Activity
{
	ListView mProviders;
	LocationManager mLocationManager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mProviders = (ListView) findViewById(R.id.providers);
		// 获取系统的LocationManager对象
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// 创建一个LocationProvider的过滤条件
		Criteria cri = new Criteria();
		// 设置要求LocationProvider必须是免费的。
		cri.setCostAllowed(false);

		// 设置要求LocationProvider能提供高度信息
		cri.setAltitudeRequired(true);  
		// 设置要求LocationProvider能提供方向信息
		cri.setBearingRequired(true);
		// 获取系统所有符合条件的LocationProvider的名称
		List<String> providerNames = mLocationManager.getProviders(cri, true);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, providerNames);
		// 使用ListView来显示所有可用的LocationProvider
		mProviders.setAdapter(adapter);
	}
}