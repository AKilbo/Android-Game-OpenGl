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
import android.util.Log;

public class Square {
	public static final String TAG = Square.class.getSimpleName();
	
	
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
	
	public Square(int color) {
		this();
		this.setColor(color);
	}
	
	public Square(float red, float green, float blue, float alpha) {
		this();
		this.setColor(red,green,blue,alpha);
	}
	
	public void render(GL10 gl) {
		Log.d(TAG, "render");
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
	
	/**
	 * If you have to get specific on your colors you can use this method and set the respective intensities of rgba.
	 * Note: intensities are floats from 0.0 to 1.0.
	 * @param red
	 * @param blue
	 * @param green
	 * @param alpha
	 */
	public void setColor(float red, float blue, float green, float alpha) {
		this.red = red >=0.0f && red <= 1.0f ? red : 0.0f;
		this.blue = blue >= 0.0f && blue <= 1.0f ? blue : 0.0f;
		this.green = green >= 0.0f && green <= 1.0f ? green : 0.0f;
		this.alpha = alpha >= 0.0f && alpha <= 1.0f ? alpha : 0.0f;
	}
	
	/**
	 * You want to use this method. You can pass android Color constants here and it figures it all out.
	 * @param color
	 */
	public void setColor(int color){
		float [] ints = ColorUtil.colorIntensity(color);
		this.red = ints[0];
		this.green = ints[1];
		this.blue = ints[2];
		this.alpha = ints[3];
	}
	
	/**
	 * Returns the color of the square as a a color int
	 * @return
	 */
	public int getColor() {
		return ColorUtil.intensityToInt(red, green, blue, alpha);
	}
	
	/**
	 * Sets the alpha level of the square using an int from 0 to 255.
	 * If the value is outside this range it defaults to 0.
	 * @param alpha
	 */
	public void setAlpha(int alpha) {
		this.alpha = alpha >= 0 && alpha <= 255 ? (float)alpha/255 : 0.0f;
	}
	
	/**
	 * Sets the alpha level of the square using a float from 0.0 to 1.0.
	 * It the value is outside this range it defaults to 0.
	 * @param alpha
	 */
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	/**
	 * Increases the alpha level.
	 * Note you can use a negative number to decrease alpha.
	 * @param delta
	 */
	public void increaseAlpha(float delta) {
		this.alpha += delta;
		if(this.alpha >= 1.0f)
			this.alpha = 1.0f;
		else if (this.alpha <= 0.0f)
			this.alpha = 0.0f;
	}
	
	/**
	 * Decreases the alpha level.
	 * Note you can use a negative number to increase alpha.
	 * @param delta
	 */
	public void decreaseAlpha(float delta) {
		this.alpha -= delta;
		if(this.alpha >= 1.0f)
			this.alpha = 1.0f;
		else if (this.alpha <= 0.0f)
			this.alpha = 0.0f;
	}
}
