package mobile.android.proximity.alert;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;

public class ProximityAlertActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	

		// 定位服务管理器对象
		LocationManager locationManager;
		// 通过getSystemService方法获得LocationManager对象
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// 定义沈阳百脑汇的大致经度、纬度
		double longitude =123.427109;
		double latitude = 41.764998;			
		// 定义半径（2000米） 
		float radius = 2000;
		
		// 定义Intent
		Intent intent = new Intent(this, ProximityAlertReciever.class);
		// 将Intent包装成PendingIntent
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

		// 添加临近警告
		locationManager.addProximityAlert(latitude, longitude, radius, -1, pendingIntent);		
	}
}