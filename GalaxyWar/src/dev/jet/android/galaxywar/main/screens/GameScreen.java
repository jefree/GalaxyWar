package dev.jet.android.galaxywar.main.screens;

import com.badlogic.gdx.Input.Keys;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.ui.EndUI;
import dev.jet.android.galaxywar.ui.GameUI;
import dev.jet.android.galaxywar.ui.PauseUI;
import dev.jet.android.galaxywar.world.World;

public class GameScreen extends AbstractScreen{
	
	World world;
	
	GameUI gui;
	EndUI eui;
	PauseUI pui;
	
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
		
		media.loadSinglePicture("pause/runUp.png");
		media.loadSinglePicture("pause/runDown.png");
		
		media.loadSinglePicture("shieldbar/edge.png");
		media.loadSinglePicture("shieldbar/shield.png");
		
		media.loadAnimPicture("explosion/asteroid/anim.atlas");
		
		media.loadAlphaLifePicture("shield.png");
		
		media.loadAnimPicture("explosion/ship/anim.atlas");
		
		media.loadFont("fonts/Comic Sans MS");
		
		world = new World(media);
		gui = new GameUI(world, media, this);
		eui = new EndUI(world, media, this);
		pui = new PauseUI(world, media, this);
		
		stage.addActor(world);
		stage.addActor(gui);	
	}
	
	public void showPause() {
		stage.addActor(pui);
	}
	
	public void showEnd() {
		gui.remove();
		stage.addActor(eui);
	}
	
	public void showGame() {
		
		stage.clear();
		
		stage.addActor(world);
		stage.addActor(gui);
		
	}
	
	@Override
	public boolean keyDown(int key) {
		
		if (key == Keys.BACK) {
			
			if (world.getState() == World.RUN){
				world.pause();
				showPause();
			
			} else if (world.getState() == World.PAUSE) {
				world.run();
				showGame();
			}
		}
		
		return true;
	}
}
