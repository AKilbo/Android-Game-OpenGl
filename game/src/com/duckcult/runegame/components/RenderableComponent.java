package com.duckcult.runegame.components;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.Component;

public abstract class RenderableComponent implements Component {
	public abstract void render(GL10 gl);
	public abstract void render(Canvas canvas);
	
	public abstract void setPosition(Position p);
}
