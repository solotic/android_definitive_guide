package mobile.android.drawable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyResource extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClick_Nine_Patch(View view)
    {
    	Intent intent = new Intent(this, Nine_Patch.class);
    	startActivity(intent);
    	
    }
    public void onClick_Layout_Include(View view)
    {
    	Intent intent = new Intent(this, Include.class);
    	startActivity(intent);
    	
    }
    public void onClick_Layer(View view)
    {
    	Intent intent = new Intent(this, Layer.class);
    	startActivity(intent);
    	
    }
    public void onClick_ButtonState(View view)
    {
    	Intent intent = new Intent(this, ButtonState.class);
    	startActivity(intent);
    	
    }
    public void onClick_Level(View view)
    {
    	Intent intent = new Intent(this, Level.class);
    	startActivity(intent);
    	
    }
    public void onClick_CrossFade(View view)
    {
    	Intent intent = new Intent(this, CrossFade.class);
    	startActivity(intent);
    	
    }
    public void onClick_Inset(View view)
    {
    	Intent intent = new Intent(this, Inset.class);
    	startActivity(intent);
    	
    }
    public void onClick_Clip(View view)
    {
    	Intent intent = new Intent(this, Clip.class);
    	startActivity(intent);
    	
    }
    public void onClick_Shape(View view)
    {
    	Intent intent = new Intent(this, Shape.class);
    	startActivity(intent);
    	
    }
}