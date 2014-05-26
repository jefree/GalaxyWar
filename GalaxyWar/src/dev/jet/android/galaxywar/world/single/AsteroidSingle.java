package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.actors.Asteroid;

public class AsteroidSingle extends Asteroid {
	
	private int maxDistanceShip; 
	protected SingleWorld world;
	
	@Override
	public void create(BaseWorld world) {
		super.create(world);
		
		this.world = (SingleWorld) world;
		maxDistanceShip = (int)(world.getWidth()*2);
	}

	@Override
	protected boolean shouldBeDestroy(float delta) {
		return GeomUtil.getDistance(getX(), getY(), 
				world.getShip().getX(), world.getShip().getY()) > maxDistanceShip;
	}

	@Override
	protected void onAstCollision(float delta) {
	}

	@Override
	public void setState(EntityState state) {
	}
}
