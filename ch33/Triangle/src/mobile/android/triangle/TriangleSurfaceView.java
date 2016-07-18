package mobile.android.triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class TriangleSurfaceView implements Renderer
{
    private Triangle triangle;
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		triangle = new Triangle();	
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		float ratio = (float) width / height;

		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		
		gl.glLoadIdentity();
		
		gl.glFrustumf(-ratio *2, ratio * 2, -2, 2, 1, 10);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		
	}

	@Override
	public void onDrawFrame(GL10 gl)
	{

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glLoadIdentity();
		gl.glTranslatef(1.0f, 0.0f, -2.0f);
		
		triangle.drawSelf(gl);
		
		gl.glLoadIdentity();
		gl.glTranslatef(-3.0f, 2.0f, -5.0f);
		triangle.drawSelf(gl);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}

}
