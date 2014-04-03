package dev.jet.android.galaxywar.world.actors;

public class Shield extends Entity {
	
	Entity defended;
	
	public void setDefended(Entity e) {
		defended = e;
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
		rotate(45*delta);
		
		if (life < 100) {	
			life += 5*delta;
		}
		
		for (Asteroid ast : world.getAsteroids()){
			
			if (ast.collide(this)) {
				
				receiveDamage(ast.getDamage());
				
				world.explosion(this, ast);
				
				ast.destroy();
			}
		}
	}
	
	public void reboot() {
		super.reboot();
		
		setPosition(0, 0);
		
		life = 50;
		enable = true;
	}
}
