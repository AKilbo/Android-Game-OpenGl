package com.duckcult.game.engine;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

import android.view.MotionEvent;

public interface DuckSurfaceView {
	public abstract boolean onTouchEvent(MotionEvent e);
	public abstract void setEntityManager(EntityManager em);
	public abstract void setAvgFPS(String avgFPS);
}
