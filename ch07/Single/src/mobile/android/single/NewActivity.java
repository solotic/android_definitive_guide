package mobile.android.single;

import mobile.android.single.instance.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
		setTitle("NewActivity Task ID:" + getTaskId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new, menu);
        return true;
    }
}
