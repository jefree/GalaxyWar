package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.MissileController;

public class Ship extends Entity {
	
	private final float MAX_ROTATION_SPEED = (float)160.0;
	
	int rOrientation;
	float rDelta;
	
	int nMissiles;
	float timeNewMissile;
	
	public void act(float delta) {
		
		timeNewMissile += delta;
		
		if (timeNewMissile > 2.0) {
			deltaMissiles(+1);
			timeNewMissile = 0;
		}
		
		if (rDelta > 0) {
			
			rotate(MAX_ROTATION_SPEED * rOrientation * rDelta * delta);
			rDelta = 0;
		}
		
		float speed[] = GeomUtil.getSides(getSpeed()*delta, getRotation());
		
		translate(speed[0], speed[1]);
	}
	
	public void reboot() {
		super.reboot();
		
		setPosition(0, 0);
		
		rOrientation = 0;
		rDelta = 0;
		
		life = 1;
		speed = 80;
		nMissiles = MissileController.MAX_MISSILES_NUMBER;
		
		enable = true;
	}
	
	public int getMissiles() {
		return nMissiles;
	}

	public void deltaMissiles(int delta) {
		
		float sign = Math.signum(delta);
		
		if ( (sign > 0 && nMissiles < MissileController.MAX_MISSILES_NUMBER) 
				|| (sign < 0 && nMissiles > 0))
		{
			this.nMissiles += delta;
		
		}
	}
	
	public void setRotParameter(float rotParam) {
		rOrientation = (int)Math.signum(rotParam);
		rDelta = Math.abs(rotParam);
	}
}
