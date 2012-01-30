package com.duckcult.runegame.components;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import com.duckcult.game.engine.opengl.Square;

public class SquareRenderer extends RenderableComponent {
	public static final String TAG = SquareRenderer.class.getSimpleName();
	
	private Square square;
	
	public SquareRenderer() {
		square = new Square(0.0f, 1.0f, 0.0f, .5f);
	}
	
	public SquareRenderer(Square square) {
		this.square = square;
	}
	
	public void render(GL10 gl) {
		Log.d(TAG,"render");
		square.render(gl);
	}
	
	public void render(Canvas canvas) {
		//do stuff
	}
	
	public void setColor(int color) {
		square.setColor(color);
	}
	
	public void setColor(float red, float green, float blue, float alpha){
		square.setColor(red, blue, green, alpha);
	}
	
	public int getColor() {
		return square.getColor();
	}

	@Override
	public void setPosition(Position p) {
		// TODO Auto-generated method stub
		//do something magic with the position information
	}
}
