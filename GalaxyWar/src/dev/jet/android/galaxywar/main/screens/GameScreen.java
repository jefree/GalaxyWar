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
		
		media.loadSinglePicture("ship.png");	
		media.loadSinglePicture("missile.png");	
		media.loadSinglePicture("asteroid.png");
		
		media.loadSinglePicture("bArrowUp.png");
		media.loadSinglePicture("bArrowDown.png");
		
		media.loadSinglePicture("bMissileUp.png");
		media.loadSinglePicture("bMissileDown.png");
		
		media.loadSinglePicture("bSpeedUp.png");
		media.loadSinglePicture("bSpeedDown.png");
		
		media.loadTapestryPicture("space.jpg");
		
		media.loadSinglePicture("missilesbar/edge.png");
		media.loadSinglePicture("missilesbar/normal.png");
		media.loadSinglePicture("missilesbar/warning.png");
		
		media.loadSinglePicture("end/back.png");
		media.loadSinglePicture("end/againUp.png");
		media.loadSinglePicture("end/againDown.png");
		media.loadSinglePicture("end/exitUp.png");
		media.loadSinglePicture("end/exitDown.png");
		
		media.loadSinglePicture("shieldbar/edge.png");
		media.loadSinglePicture("shieldbar/shield.png");
		
		media.loadAnimPicture("explosion/asteroid/anim.atlas");
		
		media.loadSinglePicture("shield.png");
		
		media.loadAnimPicture("explosion/ship/anim.atlas");
		
		media.loadFont("fonts/Comic Sans MS");
		
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
