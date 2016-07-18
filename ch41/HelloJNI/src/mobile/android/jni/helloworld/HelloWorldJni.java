package mobile.android.jni.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HelloWorldJni extends Activity
{   
	           
	String name="Jobs";  
	    
	@Override    
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
		TextView textView = new TextView(this);
		textView.setText(stringFromJNI());
		setContentView(textView);
	} 
  
	public native String stringFromJNI();
	
	static           
	{  
		System.loadLibrary("hello-jni");
	}
}  
