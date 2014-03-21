package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.UtilityMath;

public class Missile extends Entity {

	float life;
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		if (life < 0) {
			
			destroy();
			return;
		}
		
		Asteroid[] asteroids = world.getAsteroids(); 
		
		for (Asteroid a : asteroids) {
			
			if (a.isEnable()) {
				
				if (this.collide(a)) {
					a.destroy();
					destroy();
				}
			}
		}
		
		float speed[] = UtilityMath.getSides(getSpeed()*delta, getRotation());
		
		translate(speed[0], speed[1]);
		
		life -= delta;
	}

	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
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
