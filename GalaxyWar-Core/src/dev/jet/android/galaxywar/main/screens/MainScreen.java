package dev.jet.android.galaxywar.main.screens;

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
		
		media.loadJson("json/mainData.json");
		menu = new MainMenuUI(media, this);
		
		stage.addActor(menu);
		stage.setKeyboardFocus(menu);
	}
	
	public void launchSingleGame() {
		game.setScreen(new GameScreen(game));
	}
	
}
