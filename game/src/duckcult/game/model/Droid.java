package duckcult.game.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * A basic interactable bitmap, for out purposes its a temporary demo
 * based on code found here: http://www.javacodegeeks.com/2011/07/android-game-development-displaying.html
 * @author eharpste
 *
 */
public class Droid {
	private Bitmap bitmap;
	private int x;
	private int y;
	private boolean touched;
	
	public Droid(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX (int x){
		this.x=x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public boolean isTouched() {
		return touched;
	}
	
	public void setTouched (boolean touched) {
		this.touched = touched;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2),null);
	}
	
	public void handleActionDown(int eventX, int eventY) {
		if(eventX>=(bitmap.getWidth()/2)&&(eventX <=(x+bitmap.getWidth()/2))) {
			if(eventY >=(y-bitmap.getHeight()/2)&&(y<=(y+bitmap.getHeight()/2))){
				//droid touched
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
}
