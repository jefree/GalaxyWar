package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Explosion extends Entity {

	Animation anim;
	float stateTime;
	
	public void setAnimData(TextureAtlas atlas, float time) {
		anim = new Animation(time/atlas.getRegions().size, atlas.getRegions());
		image = anim.getKeyFrame(0);
		
		setWidth(image.getRegionWidth());
		setHeight(image.getRegionHeight());	
		
		setOrigin(getWidth()/2, getHeight()/2);
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
