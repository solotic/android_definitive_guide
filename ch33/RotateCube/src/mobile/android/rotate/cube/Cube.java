package mobile.android.rotate.cube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube
{
	private float v;	
    private float[] cubeVertices;	
	private FloatBuffer cubeVerticesBuffer;

	public Cube(float size)
	{
		v = size / 2;
		cubeVertices = new float[]{
		//  Zè½´æ??¹å?å¹³é?		
		-v, v, v, 
		v, v, v,
		v, v, v,
		v,-v, v,
		v,-v, v,
		-v, -v, v,
		-v, -v, v,
		-v, v, v, 
		//  Zè½´è??¹å?å¹³é?
		-v, v, -v,
		v, v, -v,
		v, v, -v,
		v,-v, -v,
		v,-v, -v,
		-v, -v, -v,
		-v, -v, -v,
		-v, v, -v,		
		//  å·?¾§å¹³é?
		-v, v, -v,
		-v, v, v,
		-v, v, v,
		-v,-v, v,
		-v,-v, v,
		-v, -v, -v,
		-v, -v, -v,
		-v, v, -v,		
		//  ?³ä¾§å¹³é?
		v, v, -v,
		v, v, v,
		v, v, v,
		v,-v, v,
		v,-v, v,
		v, -v, -v,
		v, -v, -v,
		v, v, -v,

		//  é¡¶é?
		-v, v, v,
		v, v, v,
		v, v, v,
		v,v, -v,
		v,v, -v,		
		-v, v, -v,
		-v, v, -v,
		-v, v, v,
		//  åº??
		-v, -v, v,
		v, -v, v,
		v, -v, v,
		v,-v, -v,
		v,-v, -v,
		-v, -v, -v,
		-v, -v, -v,
		-v, -v, v,
		}
		
		;
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(cubeVertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		cubeVerticesBuffer = byteBuffer.asFloatBuffer();
		cubeVerticesBuffer.put(cubeVertices);
		cubeVerticesBuffer.position(0);
		
	}

	public void drawSelf(GL10 gl)
	{
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeVerticesBuffer);
		//  ç»??6ä¸?¹³??
		/*for(int i = 0; i < 6; i++)
		{

			//gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, i*4 , 4);
		}*/
		for(int i = 0; i < 24; i++)
		{

			gl.glDrawArrays(GL10.GL_LINES,i*2 , 2);
		}
	}
}
