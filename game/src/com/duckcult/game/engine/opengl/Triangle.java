package com.duckcult.game.engine.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle {
	private FloatBuffer vertexBuffer;
	
	private float verticies[] = {
			-0.5f, -0.5f, 0.0f,
			 0.5f, -0.5f, 0.0f,
			 0.0f,  0.5f, 0.0f
	};
	
	public Triangle() {
		// allocate 4 bytes for each float coordinate
		ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(verticies.length * 4);
		vertexByteBuffer.order(ByteOrder.nativeOrder());
		
		// allocates the memory for the byte buffer
		vertexBuffer = vertexByteBuffer.asFloatBuffer();
		
		// fill the vertexBuffer with the verticies
		vertexBuffer.put(verticies);
		
		// set the cursor position to the beginning of the buffer
		vertexBuffer.position(0);
	}
	
	public void draw(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// set the colour for the triangle
		gl.glColor4f(0.0f, 1.0f, 0.0f, 0.5f);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		// Draw the verticies as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, verticies.length / 3);
		
		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}
}
