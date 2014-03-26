package dev.jet.android.galaxywar.world;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.actors.Explosion;

public class AstExplosionController extends GroupController<Explosion>{

	static int MAX_NUMBER_EXPLOSIONS = 10;
	
	public AstExplosionController(World world, Media media) {
		super(Explosion.class, world, media.getPicture("explosion/asteroid/anim"), MAX_NUMBER_EXPLOSIONS);
	}

	@Override
	public boolean shouldGenerate(float delta) {
		return false;
	}

	@Override
	public void initEntity(Explosion entity) {
		entity.reboot();	
		
		entity.setDuration(0.5f);
	}

}