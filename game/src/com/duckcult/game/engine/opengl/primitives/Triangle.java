package com.duckcult.game.engine.opengl.primitives;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Triangle extends Shape{
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
	
	public Triangle(int color) {
		this();
		this.setColor(color);
	}
	
	public Triangle(float red, float green, float blue, float alpha) {
		this();
		this.setColor(red,green,blue,alpha);
	}
	
	public void render(GL10 gl) {
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
	
	//TODO figure out how the hell you draw a triangle in canvas mode
	public void render(Canvas canvas) {
		Paint paint = new Paint(getColor());
	}
}
