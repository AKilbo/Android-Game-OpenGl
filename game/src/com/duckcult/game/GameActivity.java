package com.duckcult.game;

import com.duckcult.game.engine.MainGamePanel;
import com.duckcult.game.engine.opengl.GLRenderer;

import android.app.Activity;
import android.opengl.GLSurfaceView;
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
	private GLSurfaceView glSurfaceView;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //turns the title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //makes it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // Initiate the Open GL view and
        // create an instance with this activity
        glSurfaceView = new GLSurfaceView(this);
        
        // set our renderer to be the main renderer with
        // the current activity context
        glSurfaceView.setRenderer(new GLRenderer(this));
        setContentView(glSurfaceView);
        
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