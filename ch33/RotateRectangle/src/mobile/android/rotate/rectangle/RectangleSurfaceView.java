package mobile.android.rotate.rectangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class RectangleSurfaceView implements Renderer
{
	private Rectangle rectangle;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		rectangle = new Rectangle();

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		float ratio = (float) width / height;

		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_PROJECTION);

		gl.glLoadIdentity();

		gl.glFrustumf(-ratio * 2, ratio * 2, -2, 2, 1, 10);

		gl.glMatrixMode(GL10.GL_MODELVIEW);

	}

	private int angle1 = 0;
	private int angle2 = 0;

	@Override
	public void onDrawFrame(GL10 gl)
	{

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glLoadIdentity();

		gl.glTranslatef(0.0f, 0.0f, -2.0f);
		gl.glRotatef(angle1++, 0, 0, 1);
		rectangle.drawSelf(gl);

		gl.glLoadIdentity();
		angle2 -= 2;
		gl.glRotatef(angle2, 0, 0, 1);
		gl.glTranslatef(-3.0f, 0.0f, -4.0f);
		gl.glRotatef(45, 0.0f, 0.0f, 1.0f);
		rectangle.drawSelf(gl);

		gl.glLoadIdentity();
		gl.glRotatef(angle2, 0, 0, 1);
		gl.glTranslatef(3.0f, 0.0f, -4.0f);   
		gl.glRotatef(45, 0.0f, 0.0f, 1.0f);
		rectangle.drawSelf(gl);

		gl.glLoadIdentity();
		gl.glRotatef(angle2, 0, 0, 1);
		gl.glTranslatef(0.0f, 3.0f, -4.0f);
		gl.glRotatef(45, 0.0f, 0.0f, 1.0f);
		rectangle.drawSelf(gl);

		gl.glLoadIdentity();
		gl.glRotatef(angle2, 0, 0, 1);
		gl.glTranslatef(0.0f, -3.0f, -4.0f);
		gl.glRotatef(45, 0.0f, 0.0f, 1.0f);
		rectangle.drawSelf(gl);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

}
