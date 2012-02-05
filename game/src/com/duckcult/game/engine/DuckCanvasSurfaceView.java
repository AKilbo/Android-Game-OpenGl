package com.duckcult.game.engine;

import com.duckcult.game.R;
import com.duckcult.game.model.AnimatedEntity;
import com.duckcult.game.model.Droid;
import com.duckcult.game.model.Explosion;
import com.duckcult.runegame.subsystems.ShapeRenderingSystem;
import com.duckcult.runegame.subsystems.TouchSystem;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class DuckCanvasSurfaceView extends SurfaceView implements Callback, DuckSurfaceView{
	private EntityManager em; 
	private GameThread thread;
	private TouchSystem touchSystem;
	private ShapeRenderingSystem renderingSystem;
	private String avgFPS;

	public DuckCanvasSurfaceView(Context context, EntityManager em) {
		super(context);
		getHolder().addCallback(this);
		setEntityManager(em);
		thread = new GameThread(getHolder(), this, em);
		setFocusable(true);
	}
	
	public void setAvgFPS(String avgFPS) {
		this.avgFPS = avgFPS;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while(retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				//try again shutting down the thread
			}
		}

	}
	
	protected void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		renderingSystem.processRender(canvas);
		displayFPS(canvas,avgFPS);
		//canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.android), 10, 10 ,null);
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
		renderingSystem = new ShapeRenderingSystem(em);
	}
	
	private void displayFPS(Canvas canvas, String fps){
		if(canvas != null && fps !=null){
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps,this.getWidth()-50,20,paint);
		}
	}
}
