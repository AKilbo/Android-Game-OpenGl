package duckcult.game;

import java.text.DecimalFormat;

import android.util.Log;

public class FPSStatistics {
	private static final String TAG = FPSStatistics.class.getSimpleName();
	
	private DecimalFormat df = new DecimalFormat("0.##");
	private static final int STAT_INTERVAL = 1000;
	private static final int FPS_HISTORY_NR=10;
	private long lastStatusStore = 0;
	private long statusIntervalTimer = 0l;
	private long totalFramesSkipped = 0l;

	private int frameCountPerStatCycle = 0;
	private long totalFrameCount = 0l;
	private double fpsStore [];
	private long statsCount = 0;
	private double averageFPS = 0.0;
	
	private MainGamePanel gamePanel;
	
	public FPSStatistics(MainGamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void storeStats(int framesSkipped) {
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
			totalFramesSkipped += framesSkipped;
			//reset counters
			statusIntervalTimer = 0;
			frameCountPerStatCycle = 0;
			
			statusIntervalTimer = System.currentTimeMillis();
			lastStatusStore = statusIntervalTimer;
			gamePanel.setAvgFPS("FPS: "+df.format(averageFPS));
		}
	}
	
	public void initTimingElements() {
		fpsStore = new double[FPS_HISTORY_NR];
		for(int i = 0; i < FPS_HISTORY_NR; i++){
			fpsStore[i]=0.0;
		}
		Log.d(TAG, "Timeing elements for stats initialised");
	}
	
	public void report() {
		Log.d(TAG,"Total number of frames: "+totalFrameCount+" \nTotal number of frames skipped: "+totalFramesSkipped);
	}
}
