package com.duckcult.runegame.components;

import javax.microedition.khronos.opengles.GL10;

import com.duckcult.game.engine.opengl.Square;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.Component;

public class SquareRender extends RenderableComponent {
	private Square square;
	
	public SquareRender() {
		square = new Square();
	}
	
	public SquareRender(Square square) {
		this.square = square;
	}
	
	public void render(GL10 gl) {
		square.draw(gl);
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
}
