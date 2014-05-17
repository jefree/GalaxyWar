package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.actors.Asteroid;

public class AsteroidSingle extends Asteroid {
	
	private int maxDistanceShip; 
	
	@Override
	public void create(BaseWorld world) {
		super.create(world);
		
		maxDistanceShip = (int)(world.getWidth()*1.5);
	}

	@Override
	protected boolean shouldBeDestroy(float delta) {
		return GeomUtil.getDistance(getX(), getY(), 
				world.getShip().getX(), world.getShip().getY()) > maxDistanceShip;
	}

	@Override
	protected void onAstCollision(float delta) {
	}
}
