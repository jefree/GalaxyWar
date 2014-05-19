package dev.jet.android.galaxywar.world.actors;

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
		
		rotate(speedRotation);
		
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
