package com.duckcult.game.engine.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.duckcult.game.R;

public class Sprite extends Square {
	protected FloatBuffer textureBuffer;
	protected int[] textures = new int[1];
	
	protected float texture[] = {
			0.0f, 1.0f,
			0.0f, 0.0f,
			1.0f, 1.0f,
			1.0f, 0.0f
	};
	
	public Sprite() {
		// allocate 4 bytes for the floats
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(verticies.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		//allocates the memory from the byte buffer
		vertexBuffer = byteBuffer.asFloatBuffer();
		//fill the vertexBuffer with the vertices
		vertexBuffer.put(verticies);
		//set the cursor position to the beginning of the buffer
		vertexBuffer.position(0);
		
		byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuffer.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
	}
	
	public void render(GL10 gl) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glFrontFace(GL10.GL_CW);
		
		//set the color for the square
		//gl.glColor4f(0.0f, 1.0f, 0.0f, 0.5f);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		//draw the verticies as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, verticies.length / 3);
		
		//disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	public void loadGLTexture(GL10 gl, Context context) {
		//loading texture
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.android);
		
		//generate one texture pointer
		gl.glGenTextures(1, textures, 0);
		// and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		//create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		
		//Use Android GLUtils to specify a two-dimensional texture image from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		//Clean up
		bitmap.recycle();
	}

	@Override
	public void setColor(float red, float blue, float green, float alpha) {	}
	
	public void setColor(int color){ }
	
	
}
