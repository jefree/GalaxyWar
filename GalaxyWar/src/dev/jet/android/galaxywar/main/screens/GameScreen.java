package dev.jet.android.galaxywar.main.screens;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.ui.EndUI;
import dev.jet.android.galaxywar.ui.GameUI;
import dev.jet.android.galaxywar.world.World;

public class GameScreen extends AbstractScreen{
	
	World world;
	GameUI gui;
	EndUI eui;
	
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
		
		media.loadTapestryPicture("background", "space.jpg");
		
		media.loadSinglePicture("missilesbar/edge", "missilesbar/edge.png");
		media.loadSinglePicture("missilesbar/normal", "missilesbar/normal.png");
		media.loadSinglePicture("missilesbar/warning", "missilesbar/warning.png");
		
		media.loadSinglePicture("end/back", "end/back.png");
		media.loadSinglePicture("end/againUp", "end/againUp.png");
		media.loadSinglePicture("end/againDown", "end/againDown.png");
		
		media.loadSinglePicture("shieldbar/edge", "shieldbar/edge.png");
		media.loadSinglePicture("shieldbar/shield", "shieldbar/shield.png");
		
		world = new World(media);
		gui = new GameUI(world, media, this);
		eui = new EndUI(world, media, this);
		
		stage.addActor(world);
		stage.addActor(gui);
		
	}
	
	public void showEnd() {
		gui.remove();
		stage.addActor(eui);
	}
	
	public void showGame() {
		
		stage.clear();
		
		stage.addActor(world);
		stage.addActor(gui);
		
		System.out.println("omfg");
		
	}
	
	@Override
	public void hide() {
		
	}
	
	@Override
	public void dispose() {
		
	}
}
