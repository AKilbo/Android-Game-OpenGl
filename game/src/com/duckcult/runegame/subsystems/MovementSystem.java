package com.duckcult.runegame.subsystems;

import java.util.Set;

import com.duckcult.runegame.components.Drag;
import com.duckcult.runegame.components.Position;
import com.duckcult.runegame.components.Movement;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.SubSystem;

public class MovementSystem implements SubSystem {
	private EntityManager em;
	
	
	public MovementSystem (EntityManager entityManager){
		this.em = entityManager;
	}
	
	@Override
	public void processOneGameTick(long lastFrameTime) {
		Set<Integer> entities = em.getAllEntitiesPossessingComponent(Movement.class);
		for(Integer entity:entities) {
			Position p = em.getComponent(entity, Position.class);
			Movement s = em.getComponent(entity,Movement.class);
			p.addX(s.getXv());
			p.addY(s.getYv());
		}
	}
}
