package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.UtilityMath;

public class Missile extends Entity {
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		if (life < 0) {
			
			destroy();
			return;
		}
		
		Asteroid[] asteroids = world.getAsteroids(); 
		
		for (Asteroid a : asteroids) {
			
			if (a.isEnable() & this.collide(a)) {	
				a.destroy();
				destroy();
			}
		}
		
		float speed[] = UtilityMath.getSides(getSpeed()*delta, getRotation());
		
		translate(speed[0], speed[1]);
		
		life -= delta;
	}
	
	@Override
	public float getScreenX() {
		float deltaX = getX() - world.getShip().getX();
		return world.getWidth()/2 + deltaX;
	}
	
	@Override
	public float getScreenY() {
		float deltaY = getY() - world.getShip().getY();
		return world.getHeight()/2 + deltaY;
	}
}
