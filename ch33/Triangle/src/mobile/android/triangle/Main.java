package mobile.android.triangle;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Main extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		GLSurfaceView glSurfaceView = new GLSurfaceView(this);
		glSurfaceView.setRenderer(new TriangleSurfaceView());
		setContentView(glSurfaceView);
	}
}