package dev.jet.android.galaxywar.world.actors;

public abstract class Missile extends SoundEntity {
	
	public abstract void onAstCollision(float delta);
	public abstract void onDestroyed(float delta);
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		if (life <= 0) {
			
			onDestroyed(delta);

			destroy();
			return;
		}
		
		doAstCollision(delta);
		
		life -= delta;
	}
	
	public void doAstCollision (float delta) {
		
		for (Asteroid ast :  world.getAsteroids()) {
			
			if (this.collide(ast)) {	
				ast.life -= 1;
				destroy();

				onAstCollision(delta);
			}
		}	
	}
}
