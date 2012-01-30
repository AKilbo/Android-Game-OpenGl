package com.duckcult.runegame.components;

import android.view.MotionEvent;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.Component;

public class Drag extends InteractableComponent {

	@Override
	public void onTouchEvent(MotionEvent e, Position p) {
		float touchX = e.getX();
		float touchY = e.getY();
		switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN  : 
				if(touchX > p.getX() - width / 2 && touchX < p.getX() + width / 2){
					if(touchY > p.getY() - height/2 && touchY < p.getY() + height/2){
						touched = true;
					}
				}
				break;
			
			case MotionEvent.ACTION_MOVE :
				if(touched) {
					p.setX(e.getX());
					p.setY(e.getY());
				}
				break;
				
			case MotionEvent.ACTION_UP :
				touched = false;
				break;
				
			default :
				return;
		}
 	}

}
