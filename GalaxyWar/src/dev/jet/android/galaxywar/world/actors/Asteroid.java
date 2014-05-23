package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.GeomUtil;

public abstract class Asteroid extends Entity {
	
	float speedRotation;
	private float damage;
	
	protected abstract boolean shouldBeDestroy(float delta); 
	protected abstract void onAstCollision(float delta);
	
	@Override
	public void act(float delta) {
		
		if ( shouldBeDestroy(delta) ) 
		{	
			remove();
		}
		
		super.act(delta);
		
		rotateBy(speedRotation);
		
		doAstCollision(delta);
	}
	
	private void doAstCollision(float delta) {
		
		for (Asteroid ast : world.getAsteroids()) {
			
			if (ast != this & ast.collide(this) ) {
				
				ast.destroy();
				destroy();
				
				world.genAstExplosion(GeomUtil.midPoint(this.getCenter(), ast.getCenter()));
				
				onAstCollision(delta);
			}
		}
	}
	
	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	public float getDamage() {
		return damage;
	}

	public void setSpeedRotation(float speedRotation) {
		this.speedRotation = speedRotation;
	}
}
