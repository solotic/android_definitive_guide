package mobile.android.index.rectangle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

import android.view.View;

public class Rectangle
{

	private FloatBuffer rectangleBuffer;
	private float[] rectangleVertices = new float[]
	{ -1.5f, 1.5f, 0, 1.5f, 1.5f, 0, 1.5f, -1.5f, 0, -1.5f, -1.5f, 0 };
	private ByteBuffer indicesBuffer;
	private byte[] indices = new byte[]
	{ 0, 1, 1, 2, 2, 3, 3, 0};
//	private byte[] indices = new byte[]
	//{ 0, 1, 2, 2, 3, 0};
	public Rectangle()
	{
		ByteBuffer byteBuffer = ByteBuffer
				.allocateDirect(rectangleVertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		rectangleBuffer = byteBuffer.asFloatBuffer();
		rectangleBuffer.put(rectangleVertices);
		rectangleBuffer.position(0);
		
		indicesBuffer = ByteBuffer.allocateDirect(indices.length);
		indicesBuffer.put(indices);
		indicesBuffer.position(0);
		
	}

	public void drawSelf(GL10 gl)
	{
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rectangleBuffer);
		//gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, indicesBuffer);
		gl.glDrawElements(GL10.GL_LINES, 8, GL10.GL_UNSIGNED_BYTE, indicesBuffer);
		//gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

	}
}
