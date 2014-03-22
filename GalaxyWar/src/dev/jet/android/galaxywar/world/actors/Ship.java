package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.media.Picture;
import dev.jet.android.galaxywar.utils.UtilityMath;
import dev.jet.android.galaxywar.world.World;

public class Ship extends Entity {
	
	private final float MAX_ROTATION_SPEED = (float)160.0;
	
	int rOrientation;
	float rDelta;
	
	public void create(World _world, Picture _picture) {
		super.create(_world, _picture);
		
		rOrientation = 0;
		rDelta = 0;
		
		setPosition(0, 0);
		setRotation(0);
		
		speed = 80;
		life = 5;
	}
	
	public void act(float delta) {
		
		if (rDelta > 0) {
			
			rotate(MAX_ROTATION_SPEED * rOrientation * rDelta * delta);
			rDelta = 0;
		}
		
		float speed[] = UtilityMath.getSides(getSpeed()*delta, getRotation());
		
		translate(speed[0], speed[1]);
		
		for (Asteroid ast : world.getAsteroids()){
			
			if (ast.isEnable() & ast.collide(this)) {
				life -= 1;
				ast.destroy();
			}
		}
	}
	
	public float getScreenX(){
		return world.getWidth()/2;
	}
	
	public float getScreenY() {
		return world.getHeight()/2;
	}
	
	public void setRotParameter(float rotParam) {
		rOrientation = (int)Math.signum(rotParam);
		rDelta = Math.abs(rotParam);
	}

	
}
