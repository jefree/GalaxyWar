package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.media.AnimationPicture;
import dev.jet.android.galaxywar.media.Picture;
import dev.jet.android.galaxywar.world.World;

public class Explosion extends Entity {

	AnimationPicture sequence;
	float stateTime;
	
	@Override
	public void create(World _world, Picture _picture) {
		super.create(_world, _picture);
		
		sequence = (AnimationPicture) this.picture;
	}
	
	public void setDuration(float time) {
		sequence.setFrameDuration(time / sequence.getNFrames());
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
		
		sequence.setStateTime(stateTime);
		
		if(sequence.isFinished(stateTime)){
			destroy();
		}
	}
}
