package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.GeomUtil;

public class Missile extends Entity {
	
	public static float scoreBonus = 1.0f;
	public final static int DESTROY_SCORE = 10; 
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		if (life < 0) {
			
			scoreBonus = 1.0f;

			destroy();
			return;
		}
		
		Asteroid[] asteroids = world.getAsteroids(); 
		
		for (Asteroid ast : asteroids) {
			
			if (this.collide(ast)) {	
				ast.destroy();
				destroy();
				
				if (scoreBonus < 3.0) {
					scoreBonus += 0.1;
				} 
				
				world.explosion(this, ast);
				world.deltaScore( (int) (DESTROY_SCORE*scoreBonus) );
			}
		}
		
		float speed[] = GeomUtil.getSides(getSpeed()*delta, getRotation());
		
		translate(speed[0], speed[1]);
		
		life -= delta;
	}
	
	public void destroy() {
		super.destroy();
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
