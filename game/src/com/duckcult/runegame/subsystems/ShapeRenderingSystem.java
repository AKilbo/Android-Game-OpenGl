package com.duckcult.runegame.subsystems;

import java.util.List;
import java.util.Set;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.util.Log;

import com.duckcult.runegame.components.Position;
import com.duckcult.runegame.components.RenderableComponent;
import com.duckcult.runegame.components.ShapeRenderer;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.SubSystem;

public class ShapeRenderingSystem implements SubSystem {
	public static final String TAG = ShapeRenderingSystem.class.getSimpleName();
	
	private EntityManager em;
	
	public ShapeRenderingSystem (EntityManager entityManager){
		this.em=entityManager;
	}
	
	@Override
	public void processOneGameTick(long lastFrameTime) {
		//Log.d(TAG, "processOneGameTick");
		Set<Integer> entities = em.getAllEntitiesPossessingComponent(ShapeRenderer.class);
		for (Integer entity:entities) {
			ShapeRenderer r = em.getComponent(entity, ShapeRenderer.class);
			Position p = em.getComponent(entity,Position.class);
			r.setPosition(p);
		}
	}
	
	public void processRender(GL10 gl){
		Log.d(TAG,"processRender");
		List<ShapeRenderer> components = em.getAllComponentsOfType(ShapeRenderer.class);
		for(ShapeRenderer comp:components){
			comp.render(gl);
		}
	}
	
	public void processRender(Canvas canvas) {
		List<ShapeRenderer> components = em.getAllComponentsOfType(ShapeRenderer.class);
		for(ShapeRenderer comp:components){
			comp.render(canvas);
		}
	}

}
