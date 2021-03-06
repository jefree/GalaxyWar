package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.utils.GeomUtil;

public abstract class Shield extends Entity {
	
	Entity defended;
	float regeneration;
	
	public void setDefended(Entity e) {
		defended = e;
	}
	
	public void setRegeneration(float reg) {
		regeneration = reg;
	}
	
	public void receiveDamage(float damage) {
		
		if (life > damage) {
			life -= damage;
			
		} else {
			
			damage -= life;
			life = 0;
			
			defended.life -= damage;
		}
	}
	
	@Override
	public void act(float delta) {
		
		setPosition(defended.getX(), defended.getY());
		rotateBy(45*delta);
		
		if (life < 100) {	
			life += regeneration*delta;
		}
		
		for (Asteroid ast : world.getAsteroids()){
			
			if (ast.collide(this)) {
				
				receiveDamage(ast.damage);
				world.genAstExplosion(GeomUtil.midPoint(this.getCenter(),ast.getCenter()));
				ast.destroy();
			}
		}
	}
	
	public void reset() {
		super.reset();
		
		setPosition(0, 0);
		
		life = 50;
	}
}
