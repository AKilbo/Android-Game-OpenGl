package com.duckcult.game.engine.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.duckcult.runegame.subsystems.RenderingSystem;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.Log;

public class GLRenderer implements Renderer {
	public static final String TAG = GLRenderer.class.getSimpleName(); 
	
	private EntityManager em;
	private RenderingSystem renderingSystem;
	
	public GLRenderer(EntityManager entityManager) {
		em = entityManager;
		renderingSystem = new RenderingSystem(entityManager);
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		Log.d(TAG,"onDrawFrame");
		// clear screen and depth buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// Reset the Modelview Matrix
		gl.glLoadIdentity();

		// move 5 units INTO the screen
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
		//synchronized(em) {
			renderingSystem.processRender(gl);
		//}
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
