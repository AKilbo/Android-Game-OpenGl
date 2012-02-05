package com.duckcult.game.engine.opengl.primitives;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;

public abstract class Renderable {
	protected float x,y,height,width;
	public abstract void render(GL10 gl);
	public abstract void render(Canvas canvas);
	public void setPostion(float x, float y) {
		this.x=x;
		this.y=y;
	}
	public void setDimensions(float height, float width) {
		this.height = height;
		this.width = width;
	}
	public void setX(float x){
		this.x=x;
	}
	public float getX() {
		return x;
	}
	public void setY(float y) {
		this.y=y;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getY() {
		return y;
	}
}
