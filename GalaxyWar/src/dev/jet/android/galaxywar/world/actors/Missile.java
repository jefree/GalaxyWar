package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.GeomUtil;

public abstract class Missile extends SoundEntity {
	
	public abstract void onAstCollision(float delta);
	public abstract void onLifeZero(float delta);
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		if (life <= 0) {
			
			onLifeZero(delta);

			destroy();
			return;
		}
		
		float speed[] = GeomUtil.getSides(getSpeed()*delta, getRotation());
		
		translate(speed[0], speed[1]);
		
		doAstCollision(delta);
		
		life -= delta;
	}
	
	public void doAstCollision (float delta) {
		
		for (Asteroid ast :  world.getAsteroids()) {
			
			if (this.collide(ast)) {	
				ast.destroy();
				destroy();

				onAstCollision(delta);
				
				world.explosion(this, ast);
				
			}
		}	
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
