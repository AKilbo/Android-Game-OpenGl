package com.duckcult.game.engine.opengl.primitives;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.duckcult.game.GameActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Square extends Shape{
	public static final String TAG = Square.class.getSimpleName();
	
	protected FloatBuffer vertexBuffer;
	
	protected float verticies[] = {
			-1.0f, -1.0f, 0.0f,
			-1.0f,  1.0f, 0.0f,
			 1.0f, -1.0f, 0.0f,
			 1.0f,  1.0f, 0.0f
	};
	
	public Square() {
		if(GameActivity.useOpenGL) {
			// allocate 4 bytes for the floats
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(verticies.length * 4);
			byteBuffer.order(ByteOrder.nativeOrder());
			//allocates the memory from the byte buffer
			vertexBuffer = byteBuffer.asFloatBuffer();
			//fill the vertexBuffer with the vertices
			vertexBuffer.put(verticies);
			//set the cursor position to the beginning of the buffer
			vertexBuffer.position(0);
		}
	}
	
	public Square(int color) {
		this();
		this.setColor(color);
	}
	
	public Square(float red, float green, float blue, float alpha) {
		this();
		this.setColor(red,green,blue,alpha);
	}
	
	public void setHeight(float height) {
		this.height = this.width = height;
	}
	
	public void setWidth(float width) {
		this.height = this.width = width;
	}
	
	public void setDimensions(float height, float width) {
		this.height = this.width = height;
	}
	
	public void render(GL10 gl) {
		Log.d(TAG, "gl render");
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		//set the color for the square
		gl.glColor4f(red, green, blue, alpha);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		//draw the verticies as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, verticies.length / 3);
		
		//disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	@Override
	public void render(Canvas canvas) {
		Log.d(TAG,"canvas render");
		Paint paint = new Paint(getColor());
		canvas.drawRect(this.x,this.y,this.x+this.width,this.y+this.height, paint);
	}
}
