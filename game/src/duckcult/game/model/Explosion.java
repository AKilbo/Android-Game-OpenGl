package duckcult.game.model;

import android.graphics.Canvas;
import android.util.Log;

public class Explosion {
	public static final String TAG = Explosion.class.getSimpleName();
	
	public static final int STATE_ALIVE = 0;
	public static final int STATE_DEAD = 1;
	
	private Particle [] particles;
	private int x,y;
	private int size;
	private int state;
	
	public Explosion (int particleNum, int x, int y) {
		Log.d(TAG, "Explosion created at "+x+","+y);
		this.state = STATE_ALIVE;
		this.particles = new Particle[particleNum];
		for (int i = 0; i < this.particles.length; i++){
			Particle p = new Particle(x,y);
			this.particles[i]=p;
		}
		this.size = particleNum;
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
