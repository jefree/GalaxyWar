package dev.jet.android.galaxywar.media;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.jet.android.galaxywar.world.actors.Entity;

public class AlphaLifePicture extends SinglePicture {
	
	public float getAlpha(Entity e) {
		return e.getLife() / 100;
	}
	
	@Override
	public void draw(Entity e, SpriteBatch batch, float alphaParent) {
		
		batch.setColor(1, 1, 1, getAlpha(e));
		
		super.draw(e, batch, alphaParent);
		
		batch.setColor(1, 1, 1, 1);
	}
}
