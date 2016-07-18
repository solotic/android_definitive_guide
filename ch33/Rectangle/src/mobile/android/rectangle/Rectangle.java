package mobile.android.rectangle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Rectangle
{
	int one = 0x10000;
	private IntBuffer triangleBuffer;
	private int[] triangleVertices = new int[]
	{ 0, one, 0, -one, 0, 0, one, 0, 0 };

	public Rectangle()
	{
		ByteBuffer byteBuffer = ByteBuffer
				.allocateDirect(triangleVertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		triangleBuffer = byteBuffer.asIntBuffer();
		triangleBuffer.put(triangleVertices);
		triangleBuffer.position(0);
	}

	public void drawSelf(GL10 gl)
	{
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, triangleBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);		
        gl.glRotatef(180,0.0f, 0.0f, 1.0f);		
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);

	}

}
