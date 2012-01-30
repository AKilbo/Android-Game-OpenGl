package com.duckcult.runegame.components;

import android.view.MotionEvent;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.Component;

public abstract class InteractableComponent implements Component {
	protected float width, height;
	protected boolean touched;
	
	public abstract void onTouchEvent(MotionEvent e, Position p);

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}
}
