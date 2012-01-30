package com.duckcult.runegame.subsystems;

import java.util.List;
import java.util.Set;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.util.Log;

import com.duckcult.runegame.components.Position;
import com.duckcult.runegame.components.RenderableComponent;
import com.duckcult.runegame.components.SquareRenderer;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.SubSystem;

public class RenderingSystem implements SubSystem {
	public static final String TAG = RenderingSystem.class.getSimpleName();
	
	private EntityManager em;
	private long lastFrameTime;
	
	
	public RenderingSystem (EntityManager entityManager){
		this.em=entityManager;
	}
	
	@Override
	public void processOneGameTick(long lastFrameTime) {
		//Log.d(TAG, "processOneGameTick");
		this.lastFrameTime = lastFrameTime;
		Set<Integer> entities = em.getAllEntitiesPossessingComponent(SquareRenderer.class);
		for (Integer entity:entities) {
			SquareRenderer r = em.getComponent(entity, SquareRenderer.class);
			Position p = em.getComponent(entity,Position.class);
			r.setPosition(p);
		}
	}
	
	public void processRender(GL10 gl){
		Log.d(TAG,"processRender");
		List<SquareRenderer> components = em.getAllComponentsOfType(SquareRenderer.class);
		for(SquareRenderer comp:components){
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
