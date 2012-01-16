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
	
	private FPSStatistics stats;
	private boolean takeStats = true;
	
	private SurfaceHolder surfaceHolder;
	private MainGamePanel gamePanel;
	private boolean running;
	
	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
		if(takeStats) {
			stats = new FPSStatistics(gamePanel);
		}
	}
	
	//flag to hold the game state
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void run(){
		Canvas canvas;
		Log.d(TAG, "Starting game loop");
		if(takeStats){stats.initTimingElements();}
		
		long beginTime;
		long timeDiff;
		int sleepTime;
		int framesSkipped;
		
		long tickCount;
		tickCount = 0L;
		
		while(running){
			canvas = null;
			
			try {
				//try locking the canvas for exclusive pixel editing on the surface
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;
					//update game state
					this.gamePanel.update();
					//draws the canvas on the panel
					this.gamePanel.render(canvas);
					timeDiff = System.currentTimeMillis() - beginTime;
					sleepTime = (int)(FPSConstraints.FRAME_PERIOD - timeDiff);
					
					if(sleepTime > 0){
						//if sleepTime > 0 we're ahead of schedule
						try{
							//sleep the thread if we're ahead
							//this saves battery
							Thread.sleep(sleepTime);
						}
						catch(InterruptedException e){}
					}
					
					while(sleepTime < 0 && framesSkipped < FPSConstraints.MAX_FRAME_SKIPS) {
						//if sleepTime < 0 we're behind schedule
						this.gamePanel.update();
						sleepTime += FPSConstraints.FRAME_PERIOD;
						framesSkipped++;
					}
					
					if(framesSkipped >0) {
						Log.d(TAG,"Skipped: "+framesSkipped);
					}
					
					if(takeStats){stats.storeStats(framesSkipped);}
				}
			}
			finally {
				//in case of an exception the surface is not left in an inconsistent state
				if(canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			tickCount++;
		}
		Log.d(TAG, "Game loop executed "+tickCount +" times");
	}
}
