package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.jet.android.galaxywar.world.BaseWorld;

public class SoundEntity extends Entity {
	
	Sound sound;
	long soundId;

	public void create(BaseWorld world, Sound sound) {
		super.create(world);
		setSound(sound);
	}
	
	public void create(BaseWorld world, TextureRegion image, Sound sound) {
		super.create(world, image);
		setSound(sound);
	}
	
	public void setSound(Sound sound) {
		this.sound = sound;
	}
	
	public void playSound(float volume) {
		soundId = sound.play();
		sound.setVolume(soundId, volume);
	}
	
	public void playSound() {
		playSound(1.0f);
	}

	@Override
	public void reset() {
		super.reset();
		
		sound.stop(soundId);
		soundId = -1;
	}
	
}
