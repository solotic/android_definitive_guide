package mobile.android.triangle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle
{
	int one = 0x10000;
	private IntBuffer triangleBuffer;
	private int[] triangleVertices = new int[]
	{ 0, one, 0, -one, -one, 0, one, -one, 0 };

	public Triangle()
	{
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(triangleVertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		triangleBuffer = byteBuffer.asIntBuffer();
		triangleBuffer.put(triangleVertices);
		triangleBuffer.position(0);
	}
	public void drawSelf(GL10 gl)
	{
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, triangleBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);				
	}
	
}
