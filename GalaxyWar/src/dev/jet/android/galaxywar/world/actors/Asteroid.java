package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.audio.Sound;

import dev.jet.android.galaxywar.media.Picture;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.World;

public class Asteroid extends Entity {
	
	float speedRotation;
	float dirAngle;
	float damage;
	
	private int MAX_DISTANCE_TO_SHIP; 
	
	@Override
	public void create(World _world, Picture _picture, Sound _sound) {
		super.create(_world, _picture, _sound);
		
		MAX_DISTANCE_TO_SHIP = (int)(world.getWidth()*1.5);
	}
	
	@Override
	public void act(float delta) {
		
		float speed[];
		
		if (GeomUtil.getDistance(getX(), getY(), 
					world.getShip().getX(), world.getShip().getY()) > MAX_DISTANCE_TO_SHIP) 
		{	
			destroy();
		}
		
		super.act(delta);
		
		speed = GeomUtil.getSides(getSpeed()*delta, dirAngle);
		
		rotate(speedRotation);
		translate(speed[0], speed[1]);
		
		for (Asteroid ast : world.getAsteroids()) {
			
			if (ast != this & ast.collide(this) ) {
				
				ast.destroy();
				destroy();
				
				world.explosion(this, ast);
			}
		}
	}
	
	public void reboot() {
		super.reboot();
		
		remove();
		
		damage = 20;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public float getDirAngle() {
		return dirAngle;
	}

	public void setDirAngle(float dirAngle) {
		this.dirAngle = dirAngle;
	}

	public float getSpeedRotation() {
		return speedRotation;
	}

	public void setSpeedRotation(float speedRotation) {
		this.speedRotation = speedRotation;
	}
	
	

}
