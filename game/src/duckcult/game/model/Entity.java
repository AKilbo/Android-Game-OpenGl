package duckcult.game.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * A Basic Entity class that all things can inherit from.
 * Basically it has a position, ability to be drawn and ability to be touched.
 * @author eharpste
 *
 */
public abstract class Entity {
	public static final String TAG = Entity.class.getSimpleName();
	
	protected Bitmap bitmap;
	protected int x;
	protected int y;
	protected boolean touched;
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	public boolean isTouched(){
		return touched;
	}
	public void handleActionDown(int eventX, int eventY) {
		if(eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth() / 2))) {
			if(eventY >= (y - bitmap.getHeight() / 2) && (eventY <= (y + bitmap.getHeight() / 2))){
				setTouched(true);
			}
			else {
				setTouched(false);
			}
		}
		else {
			setTouched(false);
		}
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2),null);
	}
	
	public abstract void update(long gameTime);
	

}