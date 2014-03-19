package dev.jet.android.galaxywar.world;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.UtilityMath;
import dev.jet.android.galaxywar.world.actors.Missile;
import dev.jet.android.galaxywar.world.actors.Ship;

public class MissileController extends GroupController<Missile> {

	private static final int MAX_MISSILES_NUMBER = 15;
	
	private static final float MISSILE_LIFE = 4; 
	private static final int MISSILE_SPEED = 200; 
	
	public MissileController(World world, Media media){
		
		super(Missile.class, world, media.getPicture("missile"), MAX_MISSILES_NUMBER);
	}
	
	@Override
	public boolean shouldGenerate(float delta) {
		return false;
	}

	@Override
	public void initEntity(Missile missile) {
		
		Ship ship = getWorld().getShip();
		float delta[] = UtilityMath.getSides(ship.getHeight()/2 + missile.getHeight()/2, ship.getRotation());
		
		missile.setRotation(ship.getRotation());
		missile.setPosition(ship.getX() + delta[0],
							ship.getY() + delta[1]);
		
		missile.setSpeed(MISSILE_SPEED + world.getShip().getSpeed());
		missile.setLife(MISSILE_LIFE);
		
	}

}
