package dev.jet.android.galaxywar.world;

import java.lang.reflect.Array;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import dev.jet.android.galaxywar.world.actors.Entity;

public abstract class GroupController <T extends Entity> extends Group {

	private T[] group;
	protected World world;
	
	protected abstract boolean shouldGenerate(float delta);
	protected abstract void initEntity(T entity);
	
	public GroupController(Class <T> tClass, World world, TextureRegion image, Sound sound, int groupSize) {
		
		this.world = world;
		
		group = (T[]) Array.newInstance(tClass, groupSize);
		
		try {
		
			for (int i=0; i<groupSize; i++) {
				group[i] = tClass.newInstance();
				group[i].create(world, image, sound);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		setWidth(world.getWidth());
		setHeight(world.getHeight());
	}
	
	public void reboot() {
		
		for (Entity e : group) {
			e.reboot();
		}
	}
	
	public T genNew() {
		
		for (T e : group) {
			
			if(!e.isEnable()) {
				
				e.setEnable(true);
				
				initEntity(e);
				
				addActor(e);
				
				return e;
			}
		}
		
		return null;
	}
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		if (shouldGenerate(delta)) {
			genNew();
		}
	}
	
	public World getWorld() {
		return world;
	}
	
	public T[] getGroup() {
		return group;
	}
}
