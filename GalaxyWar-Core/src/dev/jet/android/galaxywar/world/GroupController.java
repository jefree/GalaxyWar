package dev.jet.android.galaxywar.world;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;

import dev.jet.android.galaxywar.world.actors.Entity;

public abstract class GroupController <T extends Entity> extends Group {
	
	protected BaseWorld world;
	protected ArrayList<T> entities;
	
	protected abstract T createNew();
	protected abstract void init(T obj);
	public abstract void setState(EntityState state);
	
	public GroupController(BaseWorld world) {
		
		this.world = world;
		entities = new ArrayList<T>();
		
		setWidth(world.getWidth());
		setHeight(world.getHeight());
	}
	
	public T create() {
		
		T entity = null;
		
		for (T e : entities) {
			if ( !e.enable ) {
				entity = e;
			}
		}
		
		if (entity == null) {
			entity = createNew();
			entities.add(entity);
		}
		
		entity.reset();
		init(entity);
		
		addActor(entity);
		
		return entity;
	}
	
	public BaseWorld getWorld() {
		return world;
	}
	
	public ArrayList<T> getEnabledEntities() {
		
		ArrayList<T> result = new ArrayList<T>();
		
		for (T e : entities) {
			if(e.enable) {
				result.add(e);
			}
		}
		
		return result;
	} 
	
	public void reset() {
		for (T e : entities) {
			e.destroy();
		}
	}
}
