package com.duckcult.game.engine.opengl.primitives;

import javax.microedition.khronos.opengles.GL10;

import com.duckcult.game.engine.opengl.ColorUtil;

import android.graphics.Canvas;

public abstract class Shape extends Renderable {
	public static final String TAG = Shape.class.getSimpleName();
	
	protected float red,green,blue,alpha;
	
	@Override
	public abstract void render(GL10 gl);

	@Override
	public abstract void render(Canvas canvas);

	/**
	 * If you have to get specific on your colors you can use this method and set the respective intensities of rgba.
	 * Note: intensities are floats from 0.0 to 1.0.
	 * @param red
	 * @param blue
	 * @param green
	 * @param alpha
	 */
	public void setColor(float red, float blue, float green, float alpha) {
		this.red = red >=0.0f && red <= 1.0f ? red : 0.0f;
		this.blue = blue >= 0.0f && blue <= 1.0f ? blue : 0.0f;
		this.green = green >= 0.0f && green <= 1.0f ? green : 0.0f;
		this.alpha = alpha >= 0.0f && alpha <= 1.0f ? alpha : 0.0f;
	}
	
	/**
	 * You want to use this method. You can pass android Color constants here and it figures it all out.
	 * @param color
	 */
	public void setColor(int color){
		float [] ints = ColorUtil.colorIntensity(color);
		this.red = ints[0];
		this.green = ints[1];
		this.blue = ints[2];
		this.alpha = ints[3];
	}
	
	/**
	 * Returns the color of the shape as a a color int
	 * @return
	 */
	public int getColor() {
		return ColorUtil.intensityToInt(red, green, blue, alpha);
	}
	
	/**
	 * Sets the alpha level of the shape using an int from 0 to 255.
	 * If the value is outside this range it defaults to 0.
	 * @param alpha
	 */
	public void setAlpha(int alpha) {
		this.alpha = alpha >= 0 && alpha <= 255 ? (float)alpha/255 : 0.0f;
	}
	
	/**
	 * Sets the alpha level of the shape using a float from 0.0 to 1.0.
	 * It the value is outside this range it defaults to 0.
	 * @param alpha
	 */
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	/**
	 * Increases the alpha level.
	 * Note you can use a negative number to decrease alpha.
	 * @param delta
	 */
	public void increaseAlpha(float delta) {
		this.alpha += delta;
		if(this.alpha >= 1.0f)
			this.alpha = 1.0f;
		else if (this.alpha <= 0.0f)
			this.alpha = 0.0f;
	}
	
	/**
	 * Decreases the alpha level.
	 * Note you can use a negative number to increase alpha.
	 * @param delta
	 */
	public void decreaseAlpha(float delta) {
		this.alpha -= delta;
		if(this.alpha >= 1.0f)
			this.alpha = 1.0f;
		else if (this.alpha <= 0.0f)
			this.alpha = 0.0f;
	}
}
