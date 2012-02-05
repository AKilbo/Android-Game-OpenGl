package com.duckcult.game.engine;

import com.duckcult.game.engine.opengl.DuckGLSurfaceView;
import com.duckcult.game.engine.opengl.primitives.Square;
import com.duckcult.runegame.components.Drag;
import com.duckcult.runegame.components.Position;
import com.duckcult.runegame.components.RenderableComponent;
import com.duckcult.runegame.subsystems.MovementSystem;
import com.duckcult.runegame.subsystems.ShapeRenderingSystem;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * The central game loop.
 * At its most basic this is an infinite loop but there are optimizations for it to run at a constant game speed.
 * Every cycle of the loop it will call update on the MainGamePanel
 * Then it will call render on the MainGamePanel
 * If the collective time of the update and render takes longer than specified in the FPSConstraints class it will update the game state of the MainGamePanel until it is caught up. 
 * If you want to take statistics on the frame rate see FPSStatistics.java
 * This code is originally based on sample code found here: http://www.javacodegeeks.com/2011/07/android-game-development-basic-game_05.html though it deviates somewhat now.
 * @author eharpste
 *
 */
public class GameThread extends Thread {
	
	private static final String TAG = GameThread.class.getSimpleName();
	
	/**
	 * A statistics class to measure average frame rates
	 */
	private FPSStatistics stats;
	/**
	 * A flag to control when to display frame rates.
	 * Currently hardCoded should probably have some kind of control option.
	 */
	private boolean takeStats = false;
	
	/**
	 * No idea what this thing is but its necessary
	 */
	private SurfaceHolder surfaceHolder;
	
	/**
	 * A flag for whether the game thread is running or not
	 */
	private boolean running;
	
	private DuckGLSurfaceView dglSurfaceView = null;
	private DuckCanvasSurfaceView canvasSurfaceView = null;
	
	private boolean useOGL = false;
	
	private EntityManager em;

	private MovementSystem movementSystem;
	//note this is not actually the system that renders the visual that one lives in the relevant SurfaceView
	//because of the nature of systems it doesn't actually matter that there are 2 of them
	private ShapeRenderingSystem renderingSystem;
	
	/**
	 * Constructor
	 * @param surfaceHolder	You need this for whatever reason
	 * @param gamePanel		This is the main game panel
	 */
	public GameThread(SurfaceHolder surfaceHolder, DuckGLSurfaceView surfaceView, EntityManager entityManager) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.dglSurfaceView = surfaceView;
		this.em = entityManager;
		initializeSystems();
		useOGL = true;
		if(takeStats) {
			stats = new FPSStatistics(surfaceView);
		}
	}
	
	public GameThread(SurfaceHolder surfaceHolder, DuckCanvasSurfaceView surfaceView, EntityManager entityManager) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.canvasSurfaceView = surfaceView;
		this.em = entityManager;
		initializeSystems();
		useOGL = false;
		if(takeStats) {
			stats = new FPSStatistics(surfaceView);
		}
	}

	/**
	 * Turns on the flag to record frame rate stats.
	 * If its set to true it creates a new FPSStatistics object if it didn't already exist. 
	 * @param takeThem
	 */
	public void setTakeStats(boolean takeThem) {
		if(takeThem && stats != null) {
			if(useOGL)
				stats = new FPSStatistics(dglSurfaceView);
			else
				stats = new FPSStatistics(canvasSurfaceView);
		}
		takeStats = takeThem;
	}
	
	/**
	 * Sets the flag for whether the thread is running or not.
	 * @param running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/**
	 * Activates ALL THE THINGS!!!!
	 * starts the central game loop.
	 */
	public void run(){
		Canvas canvas = null;
		Log.d(TAG, "Starting game loop");
		if(takeStats){stats.initTimingElements();}
		
		
		long beginTime;
		long timeDiff;
		int sleepTime;
		int framesSkipped;
		
		long tickCount;
		tickCount = 0L;
		
	//start initializing stuff here
		int entity = em.createEntity();
		em.addComponent(entity, new Position());
		em.addComponent(entity, new RenderableComponent(new Square()));
		em.addComponent(entity, new Drag());
	//end initializing stuff here
	
		while(running){
				//if rendering with openGL
				if(useOGL) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;
					//update game state
					this.updateSystems(beginTime);
					//draw the frame
					this.dglSurfaceView.requestRender();
					timeDiff = System.currentTimeMillis() - beginTime;
					sleepTime = (int)(FPSConstraints.FRAME_PERIOD - timeDiff);
					
					if(sleepTime > 0){
						//if sleepTime > 0 we're ahead of schedule
						try{
							//sleep the thread if we're ahead this saves battery
							Thread.sleep(sleepTime);
						}
						catch(InterruptedException e){}
					}
					
					while(sleepTime < 0 && framesSkipped < FPSConstraints.MAX_FRAME_SKIPS) {
						//if sleepTime < 0 we're behind schedule
						this.updateSystems(beginTime);
						sleepTime += FPSConstraints.FRAME_PERIOD;
						framesSkipped++;
					}
					
					if(framesSkipped >0) {
						Log.d(TAG,"Skipped: "+framesSkipped);
					}
					
					if(takeStats){stats.storeStats(framesSkipped);}
				}
				
				//if rendering wit ha standard android canvas
				else {
					try {
						//try locking the canvas for exclusive pixel editing on the surface
						canvas = this.surfaceHolder.lockCanvas();
						synchronized (surfaceHolder) {
							beginTime = System.currentTimeMillis();
							framesSkipped = 0;
							this.updateSystems(beginTime);
							this.canvasSurfaceView.render(canvas);
							timeDiff = System.currentTimeMillis() - beginTime;
							sleepTime = (int)(FPSConstraints.FRAME_PERIOD - timeDiff);
							if(sleepTime > 0){
								//if sleepTime > 0 we're ahead of schedule
								try{
									//sleep the thread if we're ahead this saves battery
									Thread.sleep(sleepTime);
								}
								catch(InterruptedException e){}
							}
							while(sleepTime < 0 && framesSkipped < FPSConstraints.MAX_FRAME_SKIPS) {
								//if sleepTime < 0 we're behind schedule
								this.updateSystems(beginTime);
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
				}
				}
			tickCount++;
			Log.d(TAG, "Game loop executed "+tickCount +" times");
		}
	
	private void initializeSystems() {
		movementSystem = new MovementSystem(em);
		renderingSystem = new ShapeRenderingSystem(em);
	}
	
	private void updateSystems (long lastFrameTime) {
		movementSystem.processOneGameTick(lastFrameTime);
		renderingSystem.processOneGameTick(lastFrameTime);
	}
}
