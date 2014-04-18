package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.GroupController;
import dev.jet.android.galaxywar.world.World;
import dev.jet.android.galaxywar.world.actors.Missile;
import dev.jet.android.galaxywar.world.actors.Ship;

public class MissileController extends GroupController<MissileSingle> {

	public static final int MAX_MISSILES_NUMBER = 7;
	
	private static final float MISSILE_LIFE = 4; 
	private static final int MISSILE_SPEED = 200; 
	
	public MissileController(World world, Media media){
		
		super(MissileSingle.class, world, media.getPicture("missile"), media.getSound("sounds/shot"), MAX_MISSILES_NUMBER);
	}
	
	@Override
	public boolean shouldGenerate(float delta) {
		return false;
	}

	@Override
	public void initEntity(MissileSingle missile) {
		
		Ship ship = getWorld().getShip();
		float delta[] = GeomUtil.getSides(ship.getHeight()/2 + missile.getHeight()/2, ship.getRotation());
		
		missile.setRotation(ship.getRotation());
		missile.setPosition(ship.getX() + delta[0],
							ship.getY() + delta[1]);
		
		missile.setSpeed(MISSILE_SPEED + world.getShip().getSpeed());
		missile.setLife(MISSILE_LIFE);
		
		long idSound = missile.playSound();
		missile.getSound().setVolume(idSound, 0.5f);
		
	}
	
	@Override
	public void reboot() {
		super.reboot();
		
		MissileSingle.bonus.reboot();
	}
}
