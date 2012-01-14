package duckcult.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * This code based on sample code found here: http://www.javacodegeeks.com/2011/07/android-game-development-basic-game_05.html
 * @author eharpste
 *
 */
public class MainThread extends Thread {
	
	private static final String TAG = MainThread.class.getSimpleName();
	
	private SurfaceHolder surfaceHolder;
	private MainGamePanel gamePanel;
	private boolean running;
	
	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}
	
	//flag to hold the game state
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void run(){
		Canvas canvas;
		long tickCount = 0L;
		Log.d(TAG, "Starting game loop");
		while(running){
			canvas = null;
			try {
				//try locking the canvas for exclusive pixel editing on the surface
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					//update game state
					this.gamePanel.update();
					//draws the canvas on the panel
					this.gamePanel.render(canvas);
					//this.gamePanel.onDraw(canvas);
				}
			}
			finally {
				//in case of an exception the surface is not left in an inconsistent state
				if(canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			tickCount++;
			//update game state
			//render state of the screen
		}
		Log.d(TAG, "Game loop executed "+tickCount +" times");
	}
}
