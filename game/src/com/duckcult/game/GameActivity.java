package com.duckcult.game;

import com.duckcult.game.engine.GameThread;
import com.duckcult.game.engine.opengl.DuckGLSurfaceView;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * The central activity that starts the game.
 * Some improvement is needed here to handle restarts and screen orientation changes.
 * @author eharpste
 *
 */
public class GameActivity extends Activity {
   
	private static final String TAG = GameActivity.class.getSimpleName();
	private DuckGLSurfaceView glSurfaceView;
	private EntityManager em;
	private GameThread thread;
	
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
        
        // Initiate the Open GL view and
        // create an instance with this activity
        glSurfaceView = new DuckGLSurfaceView(this,em);
        
        //create the game thread that will run the main loop
        //thread = new MainThread(glSurfaceView.getHolder(), glSurfaceView, em);
        
        // set our renderer to be the main renderer with
        // the current activity context
        setContentView(glSurfaceView);
        
       // thread.run();
        
        /*//the old method
        setContentView(new MainGamePanel(this));
        */
        Log.d(TAG, "View Added");
    }
    
    protected void onDestroy() {
    	Log.d(TAG,"Destroying...");
    	super.onDestroy();
    }
    
    protected void onStop() {
    	Log.d(TAG,"Stopping...");
    	super.onStop();
    }
    
    protected void onResume() {
    	super.onResume();
    	glSurfaceView.onResume();
    }
    
    protected void onPause() {
    	super.onPause();
    	glSurfaceView.onPause();
    }
}