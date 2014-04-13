package dev.jet.android.galaxywar.media;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.jet.android.galaxywar.world.actors.Entity;

public abstract class Picture {
	
	protected TextureRegion image;
	
	protected int sWidth;
	protected int sHeight;
	
	public abstract void load(String fileName);
	
	public void setScreenSize(int width, int height) {
		sWidth = width;
		sHeight = height;
	}
	
	public int getWidth() {
		return image.getRegionWidth();
	}
	
	public int getHeight() {
		return image.getRegionHeight();
	}
	
	public TextureRegion getImage() {
		return image;
	}
	
	public Texture getTexture() {
		return image.getTexture();
	}
	
	public void draw (Entity e, SpriteBatch batch, float alphaParent) {
		batch.draw(image, e.getScreenX() - e.getWidth()/2, e.getScreenY() - e.getHeight()/2, e.getOriginX(), e.getOriginY(),
                e.getWidth(), e.getHeight(), e.getScaleX(), e.getScaleY(), e.getRotation());
		
	}
	
	public void dispose() {
		image.getTexture().dispose();
	}
}
