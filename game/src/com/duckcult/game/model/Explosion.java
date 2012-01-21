package com.duckcult.game.model;

import android.graphics.Canvas;
import android.util.Log;

/**
 * A basic particle explosion.
 * This is extremely general at the moment but will probably server as a good template
 * later on, especially for spells.
 * @author eharpste
 *
 */
public class Explosion {
	public static final String TAG = Explosion.class.getSimpleName();
	
	public static final int STATE_ALIVE = 0;
	public static final int STATE_DEAD = 1;
	
	private Particle [] particles;
	
	public Explosion (int particleNum, int x, int y) {
		Log.d(TAG, "Explosion created at "+x+","+y);
		this.particles = new Particle[particleNum];
		for (int i = 0; i < this.particles.length; i++){
			Particle p = new Particle(x,y);
			this.particles[i]=p;
		}
	}
	
	public void update() {
		for (Particle p : particles) {
			p.update();
		}
	}
	
	public void draw(Canvas canvas) {
		for (Particle p :particles) {
			p.draw(canvas);
		}
	}
	
	public boolean isAlive () {
		for (Particle p:particles) {
			if(p.isAlive())
				return true;
		}
		return false;
	}
}
