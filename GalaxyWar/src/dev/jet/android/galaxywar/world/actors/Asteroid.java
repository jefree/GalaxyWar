package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.math.Vector2;

import dev.jet.android.galaxywar.media.Picture;
import dev.jet.android.galaxywar.utils.UtilityGeom;
import dev.jet.android.galaxywar.utils.UtilityMath;
import dev.jet.android.galaxywar.world.World;

public class Asteroid extends Entity {
	
	float speedRotation;
	float dirAngle;
	
	private int MAX_DISTANCE_TO_SHIP; 
	
	@Override
	public void create(World _world, Picture _picture) {
		super.create(_world, _picture);
		
		MAX_DISTANCE_TO_SHIP = (int)(world.getWidth()*1.5);
	}
	
	@Override
	public void act(float delta) {
		
		float speed[];
		
		if (UtilityMath.getDistance(getX(), getY(), 
					world.getShip().getX(), world.getShip().getY()) > MAX_DISTANCE_TO_SHIP) 
		{	
			destroy();
		}
		
		super.act(delta);
		
		speed = UtilityMath.getSides(getSpeed()*delta, dirAngle);
		
		rotate(speedRotation);
		translate(speed[0], speed[1]);
		
		for (Asteroid ast : world.getAsteroids()) {
			
			if (ast != this & ast.collide(this) ) {
				
				ast.destroy();
				destroy();
				
				world.genAstExplosion(UtilityGeom.midPoint(ast.getRectangle().getCenter(new Vector2()), 
						this.getRectangle().getCenter(new Vector2())), getRotation());
			}
		}
	}
	
	public void reboot() {
		super.reboot();
		
		remove();
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
