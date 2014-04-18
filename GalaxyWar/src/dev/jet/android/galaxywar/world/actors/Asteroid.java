package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.GeomUtil;

public abstract class Asteroid extends Entity {
	
	float speedRotation;
	float dirAngle;
	private int damage;
	
	protected abstract boolean shouldBeDestroy(float delta); 
	protected abstract void onAstCollision(float delta);
	
	@Override
	public void act(float delta) {
		
		float speed[];
		
		if ( shouldBeDestroy(delta) ) 
		{	
			destroy();
		}
		
		super.act(delta);
		
		speed = GeomUtil.getSides(getSpeed()*delta, dirAngle);
		
		rotate(speedRotation);
		translate(speed[0], speed[1]);
		
		doAstCollision(delta);
	}
	
	private void doAstCollision(float delta) {
		
		for (Asteroid ast : world.getAsteroids()) {
			
			if (ast != this & ast.collide(this) ) {
				
				ast.destroy();
				destroy();
				
				onAstCollision(delta);
				
				world.explosion(this, ast);
			}
		}
	}
	
	public void reboot() {
		super.reboot();
		
		remove();
		
	}
	
	public void setDamage(int _damage) {
		damage = _damage;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDirAngle(float dirAngle) {
		this.dirAngle = dirAngle;
	}

	public void setSpeedRotation(float speedRotation) {
		this.speedRotation = speedRotation;
	}
}
