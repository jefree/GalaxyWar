package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.math.Vector2;

import dev.jet.android.galaxywar.utils.UtilityGeom;
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
		
		for (Asteroid ast : asteroids) {
			
			if (this.collide(ast)) {	
				ast.destroy();
				destroy();
				
				world.explosion(this, ast);
			}
		}
		
		float speed[] = UtilityMath.getSides(getSpeed()*delta, getRotation());
		
		translate(speed[0], speed[1]);
		
		life -= delta;
	}
	
	public void destroy() {
		super.destroy();
		world.getShip().deltaMissiles(+1);
	}
	
	public void reboot() {
		super.reboot();
		
		remove();
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
