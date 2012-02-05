package com.duckcult.runegame.components;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;

import com.duckcult.game.engine.opengl.primitives.Renderable;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.Component;

public class RenderableComponent implements Component {
	protected Renderable renderable;
	
	public RenderableComponent(Renderable renderable){
		this.renderable = renderable;
		this.renderable.setPostion(0.0f, 0.0f);
	}
	
	public RenderableComponent(Renderable renderable, Position p) {
		this.renderable = renderable;
		setPosition(p);
	}
	
	public void render(GL10 gl) {
		renderable.render(gl);
	}
	public void render(Canvas canvas){
		renderable.render(canvas);
	}
	
	public void setPosition(Position p){
		renderable.setPostion(p.getX(), p.getY());
	}
}