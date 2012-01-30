package com.duckcult.game.engine.opengl;

import com.duckcult.runegame.subsystems.TouchSystem;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class DuckGLSurfaceView extends GLSurfaceView {
	private EntityManager em;
	private TouchSystem touchSystem;
	
	public DuckGLSurfaceView(Context context, EntityManager em) {
		super(context);
		
		setRenderer(new GLRenderer(em));
		this.setEntityManager(em);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	
	public boolean onTouchEvent(MotionEvent e){
		touchSystem.processTouchEvent(e);
		return true;
	}
	
	public void setEntityManager(EntityManager em){
		this.em = em;
		touchSystem = new TouchSystem(em);
	}
}
