package duckcult.game;

import duckcult.game.model.Droid;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
/**
 * This class based on sample code found at: http://www.javacodegeeks.com/2011/07/android-game-development-basic-game_05.html
 * @author eharpste
 *
 */
public class MainGamePanel extends SurfaceView implements Callback {

	private static final String TAG = MainGamePanel.class.getSimpleName();
	private MainThread thread;
	private Droid droid;
	
	//Constructor
	public MainGamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.android),50,50);
		thread = new MainThread(getHolder(), this);
		setFocusable(true);
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
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		droid.draw(canvas);
		//canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.android), 10, 10 ,null);
	}

}
