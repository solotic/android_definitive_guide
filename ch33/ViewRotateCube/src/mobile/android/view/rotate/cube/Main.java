package mobile.android.view.rotate.cube;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		GLSurfaceView glSurfaceView = new GLSurfaceView(this);
		glSurfaceView.setRenderer(new CubeSurfaceView());
		setContentView(glSurfaceView);
	}
}