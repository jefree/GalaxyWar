package dev.jet.android.galaxywar.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;

public class GalaxyWar extends Game {

	Screen gameScreen;
	Media media;
	
	@Override
	public void create() {
		
		media = new Media();
		gameScreen = new GameScreen(this);
		
		setScreen(gameScreen);
		
	}
	
	public Media getMedia() {
		return media;
	}
	
	

}
