package dev.jet.android.galaxywar.main.screens;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.ui.GameUI;
import dev.jet.android.galaxywar.world.World;

public class GameScreen extends AbstractScreen{
	
	World world;
	GameUI ui;
	
	public GameScreen (GalaxyWar _game) {
		super(_game);
	}
	
	@Override
	public void show() {
		
		super.show();
		
		media.loadSinglePicture("ship", "ship.png");	
		media.loadSinglePicture("missile", "missile.png");	
		media.loadSinglePicture("asteroid", "asteroid.png");
		
		media.loadSinglePicture("bArrowUp", "bArrowUp.png");
		media.loadSinglePicture("bArrowDown", "bArrowDown.png");
		
		media.loadSinglePicture("bMissileUp", "bMissileUp.png");
		media.loadSinglePicture("bMissileDown", "bMissileDown.png");
		
		media.loadSinglePicture("bSpeedUp", "bSpeedUp.png");
		media.loadSinglePicture("bSpeedDown", "bSpeedDown.png");
		
		media.loadTapestryPicture("background", "background.jpg");
		
		world = new World(media);
		ui = new GameUI(world, media);
		
		stage.addActor(world);
		stage.addActor(ui);
		
	}
	
	@Override
	public void render(float delta) {
		
		if (world.getState() == World.ENDED) {
			
			System.exit(0);
		} 
		
		super.render(delta);
	}
	
	@Override
	public void hide() {
		
	}
	
	@Override
	public void dispose() {
		
	}
}
