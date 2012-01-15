package duckcult.game;

import java.text.DecimalFormat;

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
	
	/*public static final int MAX_FPS = 50;
	private static final int MAX_FRAME_SKIPS = 5;
	public static final int FRAME_PERIOD = 1000 / MAX_FPS;*/
	
//new stuff for stats	
	private DecimalFormat df = new DecimalFormat("0.##");
	private static final int STAT_INTERVAL = 1000;
	private static final int FPS_HISTORY_NR=10;
	private long lastStatusStore = 0;
	private long statusIntervalTimer = 0l;
	private long totalFramesSkipped = 0l;
	private long framesSkippedPerStatCycle = 0l;
	
	private int frameCountPerStatCycle = 0;
	private long totalFrameCount = 0l;
	private double fpsStore [];
	private long statsCount = 0;
	private double averageFPS = 0.0;
//end new stuff
	
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
		Log.d(TAG, "Starting game loop");
		initTimingElements();
		
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
					
					framesSkippedPerStatCycle += framesSkipped;
					storeStats();
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
	
//new stuff here
	private void storeStats() {
		frameCountPerStatCycle++;
		totalFrameCount++;
		
		//check actual time
		statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer);
		
		if(statusIntervalTimer >= lastStatusStore + STAT_INTERVAL) {
			//calculate the actual frames per status check interval
			double actualFPS = (double)(frameCountPerStatCycle / (STAT_INTERVAL/1000));
			//stores the latest fps in the array
			fpsStore[(int)statsCount % FPS_HISTORY_NR] = actualFPS;
			statsCount++;
			double totalFPS = 0.0;
			//sum up the stored fps values
			for(int i = 0; i<FPS_HISTORY_NR;i++){
				totalFPS += fpsStore[i];
			}
			
			//obtain the average
			if(statsCount < FPS_HISTORY_NR) {
				//in face of the first 10 tirggers
				averageFPS = totalFPS/statsCount;
			}
			else {
				averageFPS=totalFPS/FPS_HISTORY_NR;
			}
			//saving the number of total frames skipped
			totalFramesSkipped += framesSkippedPerStatCycle;
			//reset counters
			framesSkippedPerStatCycle = 0;
			statusIntervalTimer = 0;
			frameCountPerStatCycle = 0;
			
			statusIntervalTimer = System.currentTimeMillis();
			lastStatusStore = statusIntervalTimer;
			gamePanel.setAvgFPS("FPS: "+df.format(averageFPS));
		}
	}
	
	private void initTimingElements() {
		fpsStore = new double[FPS_HISTORY_NR];
		for(int i = 0; i < FPS_HISTORY_NR; i++){
			fpsStore[i]=0.0;
		}
		Log.d(TAG+".initiatingTimeingElements()", "Timeing elements for stats initialised");
	}
//end new stuff
}
