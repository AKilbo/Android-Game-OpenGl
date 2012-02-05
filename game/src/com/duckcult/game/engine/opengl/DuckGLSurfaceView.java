package com.duckcult.game.engine.opengl;

import com.duckcult.game.engine.DuckSurfaceView;
import com.duckcult.game.engine.GameThread;
import com.duckcult.runegame.subsystems.TouchSystem;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class DuckGLSurfaceView extends GLSurfaceView implements DuckSurfaceView{
	public static final String TAG = DuckGLSurfaceView.class.getSimpleName();
	
	private EntityManager em;
	private TouchSystem touchSystem;
	private GameThread thread;
	private String avgFPS;
	
	public DuckGLSurfaceView(Context context, EntityManager em) {
		super(context);
		
		setRenderer(new GLRenderer(em));
		this.setEntityManager(em);
		thread = new GameThread(getHolder(),this,em);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	
	public boolean onTouchEvent(MotionEvent e){
		if(e.getAction() == MotionEvent.ACTION_DOWN) 
			if(e.getY() > getHeight() -50) {
				thread.setRunning(false);
				((Activity)getContext()).finish();
			}
		touchSystem.processTouchEvent(e);
		return true;
	}
	
	public void setEntityManager(EntityManager em){
		this.em = em;
		touchSystem = new TouchSystem(em);
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}
	
	public void requestRender() {
	//	Log.d(TAG, "requestRender");
		super.requestRender();
	}


	@Override
	public void setAvgFPS(String avgFPS) {
		this.avgFPS = avgFPS;	
	}
}
