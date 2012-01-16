package duckcult.game.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Particle {
	public static final int STATE_ALIVE = 0;
	public static final int STATE_DEAD = 1;
	
	public static final int DEFAULT_LIFETIME 	= 200;
	public static final int MAX_DIMENSION 		= 5;
	public static final int MAX_SPEED 			= 10;
	
	private int state;
	private float width;
	private float height;
	private float x,y;
	private double xv, yv;
	private int age;
	private int lifetime;
	private int color;
	private Paint paint;
	
	public Particle (int x, int y) {
		this.x = x;
		this.y = y;
		this.state = Particle.STATE_ALIVE;
		this.width = randInt(1,MAX_DIMENSION);
		this.height = this.width;
		this.lifetime = DEFAULT_LIFETIME;
		this.age = 0;
		this.xv = (randDouble(0, MAX_SPEED * 2) - MAX_SPEED);
		this.yv = (randDouble(0, MAX_SPEED * 2) - MAX_SPEED);
		if(xv * xv + yv * yv > MAX_SPEED * MAX_SPEED){
			xv *= 0.7;
			yv *= 0.7;
		}
		this.color = Color.argb(255, randInt(0,255), randInt(0,255), randInt(0,255));
		this.paint = new Paint(this.color);
	}
	
	public void update() {
		if(this.state != STATE_DEAD) {
			this.x += this.xv;
			this.y += this.yv;
			
			int a = this.color >>> 24;
			a -= 2;
			if (a<=0){
				this.state = STATE_DEAD;
			}
			else {
				this.color = (this.color & 0x00ffffff) + (a << 24);
				this.paint.setAlpha(a);
				this.age++;
			}
			if (this.age >= this.lifetime) {
				this.state = STATE_DEAD;
			}
		}
	}
	
	public void draw(Canvas canvas) {
		paint.setColor(this.color);
		canvas.drawRect(this.x,this.y,this.x+this.width,this.y+this.height, paint);
	}
	
	private int randInt(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}
	
	private double randDouble(double min, double max) {
		return min + Math.random() * (max - min);
	}
	
	public boolean isAlive() {
		return state == STATE_ALIVE;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public double getXv() {
		return xv;
	}

	public void setXv(double xv) {
		this.xv = xv;
	}

	public double getYv() {
		return yv;
	}

	public void setYv(double yv) {
		this.yv = yv;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getLifetime() {
		return lifetime;
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}
}
