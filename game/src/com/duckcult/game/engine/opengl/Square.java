package com.duckcult.game.engine.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.duckcult.game.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class Square {
	
	protected FloatBuffer vertexBuffer;
	
	protected float verticies[] = {
			-1.0f, -1.0f, 0.0f,
			-1.0f,  1.0f, 0.0f,
			 1.0f, -1.0f, 0.0f,
			 1.0f,  1.0f, 0.0f
	};
	
	private float red,green,blue,alpha;
	
	
	public Square() {
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
	
	public void render(GL10 gl) {
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glFrontFace(GL10.GL_CW);
		
		//set the color for the square
		gl.glColor4f(red, green, blue, alpha);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		//draw the verticies as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, verticies.length / 3);
		
		//disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	public void setColor(float red, float blue, float green, float alpha) {
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.alpha = alpha;
	}
	
	public void setColor(int color){
		this.red = ColorUtil.redIntensity(color);
		this.green = ColorUtil.greenIntensity(color);
		this.blue = ColorUtil.blueIntensity(color);
		this.alpha = ColorUtil.alphaIntensity(color);
	}
	
	public int getColor() {
		return ColorUtil.intensityToInt(red, green, blue, alpha);
	}
}
