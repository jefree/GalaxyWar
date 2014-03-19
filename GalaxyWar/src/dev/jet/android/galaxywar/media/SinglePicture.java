package dev.jet.android.galaxywar.media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SinglePicture extends Picture {

	@Override
	public void load(String fileName) {
		image = new TextureRegion(new Texture(Gdx.files.internal(fileName)));
		
	}

	@Override
	public void update() {
	}
}
