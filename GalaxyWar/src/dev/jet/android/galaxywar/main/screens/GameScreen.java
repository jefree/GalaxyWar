package dev.jet.android.galaxywar.main.screens;

import com.badlogic.gdx.Input.Keys;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.ui.game.EndUI;
import dev.jet.android.galaxywar.ui.game.GameUI;
import dev.jet.android.galaxywar.ui.game.PauseUI;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.BaseWorld.WorldState;
import dev.jet.android.galaxywar.world.single.SingleWorld;

public class GameScreen extends AbstractScreen {
	
	BaseWorld world;
	
	GameUI gui;
	PauseUI pui;
	
	public GameScreen (GalaxyWar game) {
		super(game);
	}
	
	@Override
	public void show() {
		
		super.show();
		
		media.loadTextureRegion("ship.png");	
		media.loadTextureRegion("missile.png");	
		media.loadTextureRegion("asteroid.png");
		
		media.loadTextureRegion("bArrowUp.png");
		media.loadTextureRegion("bArrowDown.png");
		
		media.loadTextureRegion("bMissileUp.png");
		media.loadTextureRegion("bMissileDown.png");
		
		media.loadTextureRegion("bSpeedUp.png");
		media.loadTextureRegion("bSpeedDown.png");
		
		media.loadTextureRegion("space.jpg");
		
		media.loadTextureRegion("missilesbar/edge.png");
		media.loadTextureRegion("missilesbar/normal.png");
		media.loadTextureRegion("missilesbar/warning.png");
		
		media.loadTextureRegion("end/back.png");
		
		media.loadTextureRegion("buttons/redUp.png");
		media.loadTextureRegion("buttons/redDown.png");
		media.loadTextureRegion("buttons/blackUp.png");
		media.loadTextureRegion("buttons/blackDown.png");
		media.loadTextureRegion("buttons/blueUp.png");
		media.loadTextureRegion("buttons/blueDown.png");
		
		media.loadTextureRegion("shieldbar/edge.png");
		media.loadTextureRegion("shieldbar/shield.png");
		
		media.loadTextureAtlas("explosion/asteroid/anim.atlas");
		
		media.loadTextureRegion("shield.png");
		
		media.loadTextureAtlas("explosion/ship/anim.atlas");
		
		media.loadFont("fonts/Comic Sans MS");
		media.loadFont("fonts/AmazDoom");
		
		media.loadSound("sounds/shot.mp3");
		media.loadSound("sounds/explosionAst.mp3");
		media.loadSound("sounds/explosionShip.mp3");
		media.loadMusic("sounds/music.mp3");
		
		world = new SingleWorld(media);
		gui = new GameUI(world, media, this);
		pui = new PauseUI(world, media, this);
		
		stage.addActor(world);
		stage.addActor(gui);
		
		world.reset();
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
		
		if(world.getState() == WorldState.PAUSE){
			world.resume();
		}
	}
	
	@Override
	public boolean keyDown(int key) {
		
		if (key == Keys.BACK) {
			
			if (world.getState() == WorldState.RUN){
				world.pause();
				showPause();
			
			} else if (world.getState() == WorldState.PAUSE) {
				showGame();
			}
		}
		
		return true;
	}
}
