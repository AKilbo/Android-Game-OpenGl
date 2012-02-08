package com.duckcult.game;

import com.duckcult.game.engine.DuckCanvasSurfaceView;
import com.duckcult.game.engine.DuckSurfaceView;
import com.duckcult.game.engine.GameThread;
import com.duckcult.game.engine.opengl.DuckGLSurfaceView;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

/**
 * The central activity that starts the game.
 * Some improvement is needed here to handle restarts and screen orientation changes.
 * @author eharpste
 * @author akilbo
 */
public class GameActivity extends Activity {
   
	private static final String TAG = GameActivity.class.getSimpleName();
	
	public static final boolean useOpenGL = true;
	private DuckSurfaceView surfaceView;
	private EntityManager em;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //turns the title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //makes it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        
        //create the entityManager the heart of the game world
        em = new EntityManager();
        
        // Initiate the surfaceView and
        // create an instance with this activity
        if(useOpenGL)
        	surfaceView = new DuckGLSurfaceView(this,em);
        else
        	surfaceView = new DuckCanvasSurfaceView(this,em);
        
        // set our renderer to be the main renderer with
        // the current activity context
        setContentView((SurfaceView)surfaceView);
        
       // thread.run();
        
        /*//the old method
        setContentView(new MainGamePanel(this));
        */
        Log.d(TAG, "View Added");
    }
    
    //logging the destruction of the (canvas?)
    protected void onDestroy() {
    	Log.d(TAG,"Destroying...");
    	super.onDestroy();
    }
    
    //logging that the program has stopped
    protected void onStop() {
    	Log.d(TAG,"Stopping...");
    	super.onStop();
    }
    
    protected void onResume() {
    	super.onResume();
    	if(useOpenGL) 
    		((DuckGLSurfaceView)surfaceView).onResume();
    }
    
    protected void onPause() {
    	super.onPause();
    	if(useOpenGL)
    		((DuckGLSurfaceView)surfaceView).onPause();
    }
}