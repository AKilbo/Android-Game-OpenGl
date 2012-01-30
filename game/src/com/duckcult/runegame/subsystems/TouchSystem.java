package com.duckcult.runegame.subsystems;

import java.util.List;
import java.util.Set;

import android.view.MotionEvent;

import com.duckcult.runegame.components.InteractableComponent;
import com.duckcult.runegame.components.Position;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.SubSystem;

public class TouchSystem implements SubSystem {
	private EntityManager em;
	private float lastFrametime;
	
	public TouchSystem (EntityManager entityManager) {
		this.em = entityManager;
	}
	
	
	@Override
	public void processOneGameTick(long lastFrameTime) {
		this.lastFrametime = lastFrameTime;
	}
	
	public void processTouchEvent(MotionEvent e){
		Set<Integer> entities = em.getAllEntitiesPossessingComponent(InteractableComponent.class);
		for (Integer entity:entities){
			InteractableComponent i = em.getComponent(entity, InteractableComponent.class);
			Position p = em.getComponent(entity, Position.class);
			i.onTouchEvent(e, p);
		}
	}
}
