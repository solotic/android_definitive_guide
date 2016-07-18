package mobile.android.implicit.call.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ImplicitCallActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_call);
    }
    public void onClick_ShowDefaultActivity(View view)
    {
    	Intent intent = new Intent("mobile.android.action.MYACTIVITY1");
    	startActivity(intent);
    }  
    public void onClick_ShowMyCategoryActivity(View view)
    {
    	
    	Intent intent = new Intent("mobile.android.action.MYACTIVITY2");
       // intent.setClassName("mobile.android.implicit.call.activity", "mobile.android.implicit.call.activity.MyActivity");
    	intent.addCategory("mobile.android.category.MYCATEGORY");

    	startActivity(intent);
    	
    }   
}
  