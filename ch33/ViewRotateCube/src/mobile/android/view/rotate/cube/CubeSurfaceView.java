package mobile.android.view.rotate.cube;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class CubeSurfaceView implements Renderer
{
	private Cube cube;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		cube = new Cube(4);

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

	private int angle = 0;
	

	@Override
	public void onDrawFrame(GL10 gl)
	{

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
  
		gl.glLoadIdentity();
        GLU.gluLookAt(gl, 1,0,2,0.5f,-1,0,1.5f,2.5f,1);		
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
		gl.glRotatef(angle++, 1, 0,0);
		gl.glRotatef(angle++, 0, 1,0);
		gl.glRotatef(angle++, 0, 0,1);
	 
		
	    cube.drawSelf(gl);
	    
	      

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
 
	}   

}
  