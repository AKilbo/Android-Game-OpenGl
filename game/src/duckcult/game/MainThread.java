package duckcult.game;

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
		long tickCount = 0L;
		Log.d(TAG, "Starting game loop");
		while(running){
			tickCount++;
			//update game state
			//render state of the screen
		}
		Log.d(TAG, "Game loop executed "+tickCount +" times");
	}
}
