package com.duckcult.runegame.subsystems;

import java.util.List;
import java.util.Set;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;

import com.duckcult.runegame.components.Position;
import com.duckcult.runegame.components.RenderableComponent;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.SubSystem;

public class RenderingSystem implements SubSystem {
	private EntityManager em;
	private long lastFrameTime;
	
	
	public RenderingSystem (EntityManager entityManager){
		this.em=entityManager;
	}
	
	@Override
	public void processOneGameTick(long lastFrameTime) {
		this.lastFrameTime = lastFrameTime;
		Set<Integer> entities = em.getAllEntitiesPossessingComponent(RenderableComponent.class);
		for (Integer entity:entities) {
			RenderableComponent r = em.getComponent(entity, RenderableComponent.class);
			Position p = em.getComponent(entity,Position.class);
			r.setPosition(p);
		}
	}
	
	public void processRender(GL10 gl){
		List<RenderableComponent> components = em.getAllComponentsOfType(RenderableComponent.class);
		for(RenderableComponent comp:components){
			comp.render(gl);
		}
	}
	
	public void processRender(Canvas canvas) {
		List<RenderableComponent> components = em.getAllComponentsOfType(RenderableComponent.class);
		for(RenderableComponent comp:components){
			comp.render(canvas);
		}
	}

}
