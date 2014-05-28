package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.math.Vector2;

public abstract class Asteroid extends Entity {
	
	public enum AsteroidType { SMALL, BIG };
	
	public AsteroidType type;
	public float damage;
	public float speedRotation;
	
	protected abstract boolean shouldBeDestroy(float delta); 
	protected abstract void onAstCollision(float delta);
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		rotateBy(speedRotation);
		
		if (shouldBeDestroy(delta)) {	
			destroy();
			return;
		}
		
		detectAstCollision(delta);
		
		if (life == 0) {
			
			System.out.println(ID + " destroy for life");
			
			destroy();
			world.genAstExplosion(new Vector2(getX(), getY()));
		}
	}
	
	private void detectAstCollision(float delta) {
		
		for (Asteroid ast : world.getAsteroids()) {
			
			if (ast != this & ast.collide(this) ) {
				
				if (this.type == AsteroidType.SMALL) {
					
					life -=1;
					
					if (ast.type == AsteroidType.SMALL) {
						ast.life -=1;
					}
					
				} 
				
				if (ast.type == AsteroidType.BIG) {
					life = 0;
					
					if (this.type == AsteroidType.BIG) {
						ast.life = 0;
					}
				}
				
				System.out.println(this + " collide with "+ ast);
			}
			
			onAstCollision(delta);
		}
	}
}
