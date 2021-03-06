package com.duckcult.game.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * An extension of the entity class that enables an animated sprite display.
 * Should probably just call this a Sprite. I should also put the interaction code here.
 * @author eharpste
 *
 */
public class AnimatedEntity extends Entity{
	private static final String TAG = AnimatedEntity.class.getSimpleName();
	
	private Rect sourceRect;
	private int frameNr;
	private int currentFrame;
	private long frameTicker;
	private int framePeriod;
	
	private int spriteWidth;
	private int spriteHeight;
	
	/**
	 * Basic constructor assumes the frames are all the same width and layout out horizontally
	 * @param bitmap
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param fps
	 * @param frameCount
	 */
	public AnimatedEntity(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount){
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		currentFrame = 0;
		frameNr = frameCount;
		spriteWidth = bitmap.getWidth()/frameCount;
		spriteHeight = bitmap.getHeight();
		sourceRect = new Rect(0,0,spriteWidth, spriteHeight);
		framePeriod = 1000/fps;
		frameTicker = 0l;
	}
	
	public void update(long gameTime){
		if(gameTime > frameTicker + framePeriod){
			frameTicker = gameTime;
			currentFrame++;
			if(currentFrame >= frameNr){
				currentFrame=0;
			}
		}
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left+spriteWidth;
	}
	
	@Override
	public void draw(Canvas canvas){
		Rect destRect = new Rect(getX(),getY(),getX()+spriteWidth, getY()+spriteHeight);
		canvas.drawBitmap(bitmap, sourceRect,destRect,null);
		canvas.drawBitmap(bitmap, 20, 150,null);
		Paint paint = new Paint();
		paint.setARGB(50,0,255,0);
		canvas.drawRect(20+(currentFrame*destRect.width()), 150, 20+(currentFrame * destRect.width())+destRect.width(), 150+destRect.height(), paint);
	}
}