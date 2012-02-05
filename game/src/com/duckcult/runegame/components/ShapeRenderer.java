package com.duckcult.runegame.components;

import com.duckcult.game.engine.opengl.primitives.Shape;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.Component;

public class ShapeRenderer extends RenderableComponent implements Component {
	private Shape shape;
	
	public ShapeRenderer(Shape shape) {
		super(shape);
	}
	
	public ShapeRenderer(Shape shape, Position p) {
		super(shape, p);
	}
	
	public Shape getShape(){
		return shape;
	}
	
	public void setShape(Shape shape){
		this.shape = shape;
	}
	
	public void setColor(int color) {
		shape.setColor(color);
	}
	
	public int getColor() {
		return shape.getColor();
	}
}
