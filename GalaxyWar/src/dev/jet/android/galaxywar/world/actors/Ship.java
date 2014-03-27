package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.UtilityMath;

public class Ship extends Entity {
	
	private final float MAX_ROTATION_SPEED = (float)160.0;
	
	int rOrientation;
	float rDelta;
	
	int missiles;
	
	public void act(float delta) {
		
		if (rDelta > 0) {
			
			rotate(MAX_ROTATION_SPEED * rOrientation * rDelta * delta);
			rDelta = 0;
		}
		
		float speed[] = UtilityMath.getSides(getSpeed()*delta, getRotation());
		
		translate(speed[0], speed[1]);
	}
	
	public void reboot() {
		super.reboot();
		
		setPosition(0, 0);
		
		rOrientation = 0;
		rDelta = 0;
		
		life = 1;
		speed = 80;
		missiles = 7;
		
		enable = true;
	}
	
	public int getMissiles() {
		return missiles;
	}

	public void deltaMissiles(int delta) {
		this.missiles += delta;
	}
	
	public void setRotParameter(float rotParam) {
		rOrientation = (int)Math.signum(rotParam);
		rDelta = Math.abs(rotParam);
	}
}
