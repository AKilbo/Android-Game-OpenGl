package com.duckcult.game.engine.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GLRenderer implements Renderer {

	private Triangle triangle;
	
	public GLRenderer() {
		this.triangle = new Triangle();
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// clear screen and depth buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// Reset the Modelview Matrix
		gl.glLoadIdentity();

		// move 5 units INTO the screen
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
		
		triangle.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0){						//prevent a divide by 0
			height = 1;
		}
		
		gl.glViewport(0,0,width,height);		//Reset the current viewport
		gl.glMatrixMode(GL10.GL_PROJECTION);	//select the projection matrix
		gl.glLoadIdentity();					//reset the projection matrix
		
		//Calculate the aspect ratio of the window
		GLU.gluPerspective(gl,45.0f,(float)width / (float)height, 0.1f, 100.0f);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);		//Select the Modelview Matrix
		gl.glLoadIdentity();					//Reset the Modelview Matrix

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

	}

}
