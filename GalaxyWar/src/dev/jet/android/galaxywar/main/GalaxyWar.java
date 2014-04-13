package dev.jet.android.galaxywar.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import dev.jet.android.galaxywar.main.screens.MainScreen;
import dev.jet.android.galaxywar.media.Media;

public class GalaxyWar extends Game {

	Media media;
	
	@Override
	public void create() {
		
		media = new Media(788, 480);
		
		Gdx.input.setCatchBackKey(true);
		
		setScreen(new MainScreen(this));
	}
	
	public Media getMedia() {
		return media;
	}
	
	

}
