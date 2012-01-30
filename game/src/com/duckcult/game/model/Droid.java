package com.duckcult.game.model;

import com.duckcult.runegame.components.Movement;

import android.graphics.Bitmap;

/**
 * A basic interactable bitmap, for our purposes its a temporary demo.
 * Probably to the point that we can get rid of this.
 * based on code found here: http://www.javacodegeeks.com/2011/07/android-game-development-displaying.html
 * @author eharpste
 *
 */
public class Droid extends Entity{
	private Movement speed;
	
	public Droid(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.speed = new Movement(40,40);
	}
	
	public Movement getSpeed() {
		return speed;
	}

	public void setSpeed(Movement speed) {
		this.speed = speed;
	}

	public Bitmap getBitmap(){
		return bitmap;
	}

	@Override
	public void update(long gameTime) {
		if(!touched) {
			x+=(speed.getXv());
			y+=(speed.getYv());
		}
	}
}
