package dev.jet.android.galaxywar.main.screens;

import com.badlogic.gdx.Input.Keys;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.ui.MainMenuUI;

public class MainScreen extends AbstractScreen {
	
	MainMenuUI menu;
	
	public MainScreen(GalaxyWar _game) {
		super(_game);
	}
	
	@Override
	public void show() {
		super.show();
		
		media.loadSinglePicture("main/back.png");
		
		media.loadSinglePicture("buttons/greenUp.png");
		media.loadSinglePicture("buttons/greenDown.png");
		media.loadSinglePicture("buttons/blueUp.png");
		media.loadSinglePicture("buttons/blueDown.png");
		media.loadSinglePicture("buttons/blackUp.png");
		media.loadSinglePicture("buttons/blackDown.png");
		
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
