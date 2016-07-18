package mobile.android.vertex.rectangle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Rectangle
{

	private FloatBuffer rectangleBuffer;
	private float[] rectangleVertices = new float[]
	{ -1.5f, 1.5f, 0, 1.5f, 1.5f, 0, 1.5f, -1.5f, 0, -1.5f, -1.5f, 0 };

	public Rectangle()
	{
		ByteBuffer byteBuffer = ByteBuffer
				.allocateDirect(rectangleVertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		rectangleBuffer = byteBuffer.asFloatBuffer();
		rectangleBuffer.put(rectangleVertices);
		rectangleBuffer.position(0);
	}

	public void drawSelf(GL10 gl)
	{
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rectangleBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

	}

}
