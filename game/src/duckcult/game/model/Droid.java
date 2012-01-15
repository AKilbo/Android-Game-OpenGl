package duckcult.game.model;

import duckcult.game.model.components.Speed;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * A basic interactable bitmap, for out purposes its a temporary demo
 * based on code found here: http://www.javacodegeeks.com/2011/07/android-game-development-displaying.html
 * @author eharpste
 *
 */
public class Droid extends Entity{
	private Speed speed;
	
	public Droid(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.speed = new Speed(40,40);
	}
	
	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public Bitmap getBitmap(){
		return bitmap;
	}

	@Override
	public void update(long gameTime) {
		if(!touched) {
			x+=(speed.getXv()*speed.getxDirection());
			y+=(speed.getYv()*speed.getyDirection());
		}
	}
}
