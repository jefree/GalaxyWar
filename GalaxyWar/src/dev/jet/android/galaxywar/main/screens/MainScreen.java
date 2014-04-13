package dev.jet.android.galaxywar.main.screens;

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
		
		menu = new MainMenuUI();
	}
	
}
