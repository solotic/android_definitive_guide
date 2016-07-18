package mobile.android.location.demo;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;


public class LocationActivity extends Activity
{
	// 定义LocationManager对象
	LocationManager mLocationManager;
	// 定义程序界面中的EditText组件
	EditText mEditText;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取EditText对象
		mEditText = (EditText) findViewById(R.id.show);
		// 创建LocationManager对象
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); 
		// 从GPS获取最近的定位信息
		Location location = mLocationManager.getLastKnownLocation(
			LocationManager.GPS_PROVIDER);
		// 使用location根据EditText的显示
		updateView(location);
		// 设置每2秒获取一次GPS的定位信息
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER 
			, 2000, 8, new LocationListener()
		{
			@Override
			public void onLocationChanged(Location location)
			{
				// 当GPS定位信息发生改变时，更新位置
				updateView(location);
			}

			@Override
			public void onProviderDisabled(String provider)
			{
				updateView(null);				
			}

			@Override
			public void onProviderEnabled(String provider)
			{
				// 当GPS LocationProvider可用时，更新位置
				updateView(mLocationManager
					.getLastKnownLocation(provider));				
			}

			@Override
			public void onStatusChanged(String provider, int status,
				Bundle extras)
			{
			}
		}); 

	}	

	// 更新EditText中显示的内容
	public void updateView(Location newLocation)
	{
		if (newLocation != null)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("实时的位置信息：\n");
			sb.append("经度：");
			sb.append(newLocation.getLongitude());
			sb.append("\n纬度：");
			sb.append(newLocation.getLatitude());
			sb.append("\n高度：");
			sb.append(newLocation.getAltitude());
			sb.append("\n速度：");
			sb.append(newLocation.getSpeed());
			sb.append("\n方向：");
			sb.append(newLocation.getBearing());			
			mEditText.setText(sb.toString());
		}
		else
		{ 
			// 如果传入的Location对象为空则清空EditText
			mEditText.setText("");
		}
	}
}