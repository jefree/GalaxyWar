package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.World;
import dev.jet.android.galaxywar.world.actors.Asteroid;

public class AsteroidSingle extends Asteroid {
	
	private int maxDistanceShip; 
	
	@Override
	public void create(World world, TextureRegion image, Sound sound) {
		super.create(world, image, sound);
		
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
