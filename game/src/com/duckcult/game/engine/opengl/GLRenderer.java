package com.duckcult.game.engine.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GLRenderer implements Renderer {

	private Triangle triangle;
	private Square square;
	private Context context;
	
	public GLRenderer(Context context) {
		this.triangle = new Triangle();
		this.square = new Square();
		this.context = context;
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// clear screen and depth buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// Reset the Modelview Matrix
		gl.glLoadIdentity();

		// move 5 units INTO the screen
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
		
		//triangle.draw(gl);
		square.draw(gl);
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
		square.loadGLTexture(gl, this.context);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);			//enable texture mapping
		gl.glShadeModel(GL10.GL_SMOOTH);			//enable smooth shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);	//black background
		gl.glClearDepthf(1.0f);						//depth buffer setup
		gl.glEnable(GL10.GL_DEPTH_TEST);			//enables depth testing
		gl.glDepthFunc(GL10.GL_LEQUAL);				//the type of depth testing to do
		
		//really nice perspective calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

	}

}
