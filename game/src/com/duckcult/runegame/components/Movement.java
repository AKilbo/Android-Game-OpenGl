package com.duckcult.runegame.components;

import com.artemis.utils.FastMath;
import com.duckcult.game.engine.FPSConstraints;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.Component;

/**
 * A general component used to represent entities capable of having a speed
 * this is based partially on code found here: http://www.javacodegeeks.com/2011/07/android-game-development-moving-images.html
 * @author eharpste
 *
 */
public class Movement implements Component{
	private float xv = 1;
	private float yv = 1;
	
	/**
	 * Default Constructor
	 * sets x and y velocities to 1
	 */
	public Movement() {
		this.setXv(1);
		this.setYv(1);
	}
	
	/**
	 * Non-Default Constructor
	 * sets the x and y velocities
	 * @param xVelocity	The velocity in the X direction in pixels/sec
	 * @param yVelocity	The velocity in the y direction in pixels/sec
	 */
	public Movement(float xVelocity, float yVelocity) {
		this.setXv(FPSConstraints.PStoPU(xVelocity));
		this.setYv(FPSConstraints.PStoPU(yVelocity));
	}
	
	/**
	 * Sets the velocity in units/sec at an angle in degrees
	 * @param speed	A speed in units/sec
	 * @param angle	An angle in degrees (the %360 will be done for you)
	 */
	public void setVelocity(float speed, float angle) {
		angle %= 360;
		this.setXv(FPSConstraints.PStoPU((float)FastMath.cos(angle)*speed));
		this.setYv(FPSConstraints.PStoPU((float)FastMath.sin(angle)*speed));
	}

	public float getXv() {
		return xv;
	}

	public void setXv(float xv) {
		this.xv = xv;
	}

	public float getYv() {
		return yv;
	}

	public void setYv(float yv) {
		this.yv = yv;
	}

	public void flipXDirection() {
		xv = -xv;
	}
	
	public void flipYDirection() {
		yv = -yv;
	}
	
	public float getAngle() {
		return (float)FastMath.atan(yv/xv);
	}
	
	public float getVelocity() {
		return (float)FastMath.sqrt(xv*xv+yv*yv);
	}
}
