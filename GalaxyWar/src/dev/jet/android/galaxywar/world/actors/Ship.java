package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.math.Vector2;

import dev.jet.android.galaxywar.world.EntityState;

public class Ship extends Entity {
	
	private final float MAX_ROTATION_SPEED = (float)120.0;
	
	public int rOrientation;
	float rDelta;
	
	int nMissiles;
	float timeNewMissile;
	
	public void shoot() {
		
		if (isEnable() && nMissiles > 0) {
			
			nMissiles -= 1;
			
			Vector2 pos = new Vector2(getX(), getY());
			world.genMissile(pos, getDirection());
		}
	}
	
	public void act(float delta) {
		super.act(delta);
		
		timeNewMissile += delta;
		
		if (timeNewMissile > 1.5) {
			deltaMissiles(+1);
			timeNewMissile = 0;
		}
		
		if (rDelta > 0) {
			
			rotate(MAX_ROTATION_SPEED * rOrientation * rDelta * delta);
			setDirection(getRotation());
			rDelta = 0;
		}
		
		rOrientation = 0;
	}
	
	public void reset() {
		super.reset();
		
		setPosition(0, 0);
		setDirection(0);
		
		rOrientation = 0;
		rDelta = 0;
		
		life = 1;
		speed = 80;
		nMissiles = 7;
		
	}
	
	public int getMissiles() {
		return nMissiles;
	}

	public void deltaMissiles(int delta) {
		
		float sign = Math.signum(delta);
		
		if ( (sign > 0 && nMissiles < 7) 
				|| (sign < 0 && nMissiles > 0))
		{
			this.nMissiles += delta;
		
		}
	}
	
	public void setRotParameter(float rotParam) {
		rOrientation = (int)Math.signum(rOrientation + rotParam);
		rDelta = Math.abs(rotParam);
	}

	@Override
	public void setState(EntityState state) {
		// TODO Auto-generated method stub
		
	}
}
