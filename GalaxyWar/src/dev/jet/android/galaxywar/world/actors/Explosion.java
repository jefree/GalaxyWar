package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.graphics.g2d.Animation;

public class Explosion extends Entity {

	Animation anim;
	float stateTime;
	
	public void setAnimation(Animation anim) {
		this.anim = anim;
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
