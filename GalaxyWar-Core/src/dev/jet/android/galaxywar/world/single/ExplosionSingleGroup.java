package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.GroupController;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.actors.Explosion;

public class ExplosionSingleGroup extends GroupController<Explosion>{

	private final float EXPLOSION_DURATION = 0.5f;
	TextureAtlas atlas;
	
	Sound sound;
	
	public ExplosionSingleGroup(BaseWorld world, Media media) {
		super(world);
		
		atlas = media.getTextureAtlas("explosion/asteroid/anim");
		sound = media.getSound("sounds/explosionAst");
	}

	@Override
	protected Explosion createNew() {
		
		Explosion e = new Explosion();
		e.create(world, sound);
		e.setAnimData(atlas, EXPLOSION_DURATION);
		
		return e;	
	}
	
	@Override
	protected void init(Explosion e){
		e.playSound(0.3f);
	}

	@Override
	public void setState(EntityState state) {
	}

}
