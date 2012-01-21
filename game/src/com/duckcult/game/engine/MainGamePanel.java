package com.duckcult.game.engine;

import com.duckcult.game.model.AnimatedEntity;
import com.duckcult.game.model.Droid;
import com.duckcult.game.model.Explosion;
import com.duckcult.game.model.components.Speed;

import com.duckcult.game.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
/**
 * This class is basically a trash file for testing out new things. It contains a bunch of hard coded values
 * and workarounds and not much time was spent in making it clean or effecient but feel free to use it to play with.
 * This class based on sample code found at: http://www.javacodegeeks.com/2011/07/android-game-development-basic-game_05.html
 * @author eharpste
 *
 */
public class MainGamePanel extends SurfaceView implements Callback {

	private static final String TAG = MainGamePanel.class.getSimpleName();
	private static final int EXPLOSION_SIZE = 200;
	private MainThread thread;
	private Droid droid;
	private AnimatedEntity elaine;
	private String avgFPS;
	private Explosion[] explosions;
	
	//Constructor
	public MainGamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		elaine = new AnimatedEntity(BitmapFactory.decodeResource(getResources(), R.drawable.walk_elaine),10,50,30,47,5,5);
		droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.android),50,50);
		explosions = new Explosion[10];
		thread = new MainThread(getHolder(), this);
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
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			droid.handleActionDown((int)event.getX(),(int)event.getY());
			int currentExplosion = 0;
			Explosion explosion = explosions[currentExplosion];
			
			while(explosion != null && explosion.isAlive() && currentExplosion < explosions.length-1) {
				currentExplosion++;
				explosion = explosions[currentExplosion];
			}
			if (explosion == null || !explosion.isAlive()) {
				explosion = new Explosion(EXPLOSION_SIZE, (int)event.getX(),(int)event.getY());
				explosions[currentExplosion] = explosion;
			}
			if(event.getY() > getHeight() -50) {
				thread.setRunning(false);
				((Activity)getContext()).finish();
			}
			else {
				Log.d(TAG, "Coords: x="+event.getX() +",y="+event.getY());
			}
		}
		if(event.getAction() == MotionEvent.ACTION_MOVE) {
			//the gestures
			if(droid.isTouched()) {
				droid.setX((int)event.getX());
				droid.setY((int)event.getY());
			}
		}
		if(event.getAction() == MotionEvent.ACTION_UP) {
			if(droid.isTouched()) {
				droid.setTouched(false);
			}
		}
		return true;
	}
	
	protected void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		droid.draw(canvas);
		elaine.draw(canvas);
		for (Explosion e:explosions) {
			if(e!=null)
				e.draw(canvas);
		}
		displayFPS(canvas,avgFPS);
		//canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.android), 10, 10 ,null);
	}

	public void update() {
		if(droid.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT 
				&& droid.getX() +droid.getBitmap().getWidth()/2 >= getWidth()) {
			droid.getSpeed().flipXDirection();
		}
		if(droid.getSpeed().getxDirection() == Speed.DIRECTION_LEFT 
				&& droid.getX() -droid.getBitmap().getWidth()/2 <=0) {
			droid.getSpeed().flipXDirection();
		}
		if(droid.getSpeed().getyDirection() == Speed.DIRECTION_DOWN 
				&& droid.getY() +droid.getBitmap().getHeight()/2 >= getHeight()) {
			droid.getSpeed().flipYDirection();
		}
		if(droid.getSpeed().getyDirection() == Speed.DIRECTION_UP 
				&& droid.getY() -droid.getBitmap().getHeight()/2 <= 0) {
			droid.getSpeed().flipYDirection();
		}
		elaine.update(System.currentTimeMillis());
		droid.update(System.currentTimeMillis());
		for(Explosion e:explosions) {
			if(e!=null)
				e.update();
		}
	}
	
	private void displayFPS(Canvas canvas, String fps){
		if(canvas != null && fps !=null){
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps,this.getWidth()-50,20,paint);
		}
	}

}
