package com.duckcult.game.engine;

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
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MainGamePanel(this));
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
}