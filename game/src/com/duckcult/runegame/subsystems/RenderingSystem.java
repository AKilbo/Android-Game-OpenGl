package com.duckcult.runegame.subsystems;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.duckcult.runegame.components.RenderableComponent;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.SubSystem;

public class RenderingSystem implements SubSystem {
	private EntityManager entityManager;
	private long lastFrameTime;
	
	
	public RenderingSystem (EntityManager entityManager){
		this.entityManager=entityManager;
	}
	
	@Override
	public void processOneGameTick(long lastFrameTime) {
		this.lastFrameTime = lastFrameTime; 
	}
	
	public void render(GL10 gl){
		List<RenderableComponent> components = entityManager.getAllComponentsOfType(RenderableComponent.class);
		for(RenderableComponent comp:components){
			comp.render(gl);
		}
	}

}
