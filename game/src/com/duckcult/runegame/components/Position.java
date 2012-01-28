package com.duckcult.runegame.components;

import com.artemis.utils.ArtemisUtils;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.Component;

public class Position implements Component {
	private float x,y, rotation;
	
	public Position () {
		x=0f;
		y=0f;
		rotation = 0f;
	}
	
	public Position (float x, float y){
		this.x=x;
		this.y=y;
		this.rotation = 0f;
	}
	
	public Position (float x, float y, float rotation) {
		this(x,y);
		this.rotation = rotation;
	}
	
	public void addX(float x){
		this.x += x;
	}
	
	public void addY(float y) {
		this.y += y;
	}
	
	public void addRotation(float angle){
		rotation = (rotation +angle)%360;
	}
	
	public float getRotationAsRadians() {
		return (float)Math.toRadians(rotation);
	}
	
	public float getDistanceTo(Position p){
		return ArtemisUtils.distance(x, y, p.getX(), p.getY());
	}
	
	public void setPostion (float x, float y){
		this.x=x;
		this.y=y;
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

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	
	
	
}
