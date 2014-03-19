package dev.jet.android.galaxywar.world;

import dev.jet.android.galaxywar.media.Picture;
import dev.jet.android.galaxywar.media.TapestryPicture;
import dev.jet.android.galaxywar.world.actors.Entity;

public class Background extends Entity {
	
	private static float INIT_BACK_X = 0;
	private static float INIT_BACK_Y = 0;
	
	@Override
	public void create(World _world, Picture _picture) {
		super.create(_world, _picture);
		
		((TapestryPicture)picture).setInitPosition(INIT_BACK_X, INIT_BACK_Y);

	}
}
