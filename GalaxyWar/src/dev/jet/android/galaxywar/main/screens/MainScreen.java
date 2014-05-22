package dev.jet.android.galaxywar.main.screens;

import com.badlogic.gdx.Input.Keys;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.ui.MainMenuUI;

public class MainScreen extends AbstractScreen {
	
	MainMenuUI menu;
	
	public MainScreen(GalaxyWar game) {
		super(game);
	}
	
	@Override
	public void show() {
		super.show();
		
		media.loadTextureRegion("main/back.png");
		
		media.loadTextureRegion("buttons/greenUp.png");
		media.loadTextureRegion("buttons/greenDown.png");
		media.loadTextureRegion("buttons/blueUp.png");
		media.loadTextureRegion("buttons/blueDown.png");
		media.loadTextureRegion("buttons/blackUp.png");
		media.loadTextureRegion("buttons/blackDown.png");
		
		media.loadFont("fonts/Complex");
		media.loadFont("fonts/AmazDoom");
		
		media.loadMusic("sounds/main.mp3");
		
		menu = new MainMenuUI(media, this);
		
		stage.addActor(menu);
	}
	
	public void launchSingleGame() {
		game.setScreen(new GameScreen(game));
	}
	
	@Override
	public boolean keyDown(int key) {
	
		if (key == Keys.BACK) {
			System.exit(0);
		}
		
		return true;
	}
	
}
