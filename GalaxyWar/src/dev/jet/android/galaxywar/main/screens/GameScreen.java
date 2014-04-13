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
		
		media.loadSinglePicture("buttons/redUp.png");
		media.loadSinglePicture("buttons/redDown.png");
		media.loadSinglePicture("buttons/blackUp.png");
		media.loadSinglePicture("buttons/blackDown.png");
		media.loadSinglePicture("buttons/blueUp.png");
		media.loadSinglePicture("buttons/blueDown.png");
		
		media.loadSinglePicture("shieldbar/edge.png");
		media.loadSinglePicture("shieldbar/shield.png");
		
		media.loadAnimPicture("explosion/asteroid/anim.atlas");
		
		media.loadAlphaLifePicture("shield.png");
		
		media.loadAnimPicture("explosion/ship/anim.atlas");
		
		media.loadFont("fonts/Comic Sans MS");
		media.loadFont("fonts/AmazDoom");
		
		media.loadSound("sounds/shot.mp3");
		media.loadSound("sounds/explosionAst.mp3");
		media.loadSound("sounds/explosionShip.mp3");
		media.loadMusic("sounds/music.mp3");
		
		world = new World(media);
		gui = new GameUI(world, media, this);
		pui = new PauseUI(world, media, this);
		
		stage.addActor(world);
		stage.addActor(gui);
		
		world.run();
	}
	
	public void backMainMenu() {
		game.setScreen(new MainScreen(game));
	}
	
	public void showPause() {
		stage.addActor(pui);
	}
	
	public void showEnd() {
		
		EndUI eui = new EndUI(world, media, this);
		
		gui.remove();
		stage.addActor(eui);
	}
	
	public void showGame() {
		
		stage.clear();
		
		stage.addActor(world);
		stage.addActor(gui);
		
		 if(world.getState() == World.PAUSE){
			 world.run();
		 }
	}
	
	@Override
	public boolean keyDown(int key) {
		
		if (key == Keys.BACK) {
			
			if (world.getState() == World.RUN){
				world.pause();
				showPause();
			
			} else if (world.getState() == World.PAUSE) {
				showGame();
			}
		}
		
		return true;
	}
}
