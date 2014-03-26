package dev.jet.android.galaxywar.media;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AnimationPicture extends Picture {

	Animation anim;
	TextureAtlas atlas;
	
	float frameDuration;
	
	public int getNFrames() {
		return atlas.getRegions().size; 
	}
	
	public void setFrameDuration(float frameDuration) {
		
		this.frameDuration = frameDuration;
		
		anim = new Animation(frameDuration, atlas.getRegions());
		
		image = anim.getKeyFrame(0);
	}
	
	public void setStateTime(float time) {
		image = anim.getKeyFrame(time);
	}
	
	public void load(String fileName) {
		atlas = new TextureAtlas(fileName);
		
		image = atlas.getRegions().get(0);
	}
	
	public boolean isFinished(float time) {
		return anim.isAnimationFinished(time);
	}
}
