package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.math.Vector2;

import dev.jet.android.galaxywar.world.EntityState;

public class Ship extends Entity {
	
	private final float MAX_ROTATION_SPEED = (float)120.0;
	private final int MAX_MISSILES_NUMBER = 7;
	
	public int rOrientation;
	float rDelta;
	
	int missilesN;
	float timeNewMissile;
	
	public void shoot() {
		
		if (life >= 0 && missilesN > 0) {
			
			missilesN -= 1;
			
			Vector2 pos = new Vector2(getX(), getY());
			world.genMissile(pos, direction);
		}
	}
	
	public void act(float delta) {
		super.act(delta);
		
		timeNewMissile += delta;
		
		if (timeNewMissile > 1.5) {
			
			if (missilesN < MAX_MISSILES_NUMBER) {
				missilesN += 1;
			}
			
			timeNewMissile = 0;
		}
		
		if (rDelta > 0) {
			
			rotateBy(MAX_ROTATION_SPEED * rOrientation * rDelta * delta);
			setDirection(getRotation());
			rDelta = 0;
		}
		
		rOrientation = 0;
	}
	
	public void reset() {
		super.reset();
		
		setPosition(0, 0);
		setDirection(0);
		setRotation(0);
		
		rOrientation = 0;
		rDelta = 0;
		
		life = 1;
		speed = 80;
		missilesN = MAX_MISSILES_NUMBER;
	}
	
	public int getMissiles() {
		return missilesN;
	}
	
	public void setRotParameter(float rotParam) {
		rOrientation = (int)Math.signum(rOrientation + rotParam);
		rDelta = Math.abs(rotParam);
	}

	@Override
	public void setState(EntityState state) {
		
	}
}
