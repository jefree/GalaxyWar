package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import dev.jet.android.galaxywar.world.World;

public class Explosion extends Entity {

	Animation anim;
	TextureAtlas atlas;
	
	float stateTime;
	
	public void create(World world, TextureAtlas atlas, Sound sound, float durationTime) {
		super.create(world, null, sound);
		
		this.atlas = atlas; 
		anim = new Animation(durationTime/atlas.getRegions().size, atlas.getRegions());
	}
	
	@Override
	public void reboot() {
		super.reboot();
		
		stateTime = 0;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		remove();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		stateTime += delta;
		image = anim.getKeyFrame(stateTime);
		
		if(anim.isAnimationFinished(stateTime)){
			destroy();
		}
	}
	
	public boolean isFinished() {
		return anim.isAnimationFinished(stateTime);
	}
}
