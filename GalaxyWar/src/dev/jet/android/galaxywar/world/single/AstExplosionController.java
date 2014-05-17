package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.GroupController;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.actors.Explosion;

public class AstExplosionController extends GroupController<Explosion>{

	static int MAX_NUMBER_EXPLOSIONS = 10;
	TextureAtlas atlas;
	
	Sound sound;
	
	public AstExplosionController(BaseWorld world, Media media) {
		super(Explosion.class, world, MAX_NUMBER_EXPLOSIONS);
		
		atlas = media.getTextureAtlas("explosion/asteroid/anim");
		sound = media.getSound("sounds/explosionAst");
	}

	@Override
	public boolean shouldGenerate(float delta) {
		return false;
	}

	@Override
	public void initEntity(Explosion entity) {
		entity.reboot();	
		
		entity.setSound(sound);
		entity.setAnimData(atlas, 0.5f);
		
		entity.playSound(0.3f);
	}
}
