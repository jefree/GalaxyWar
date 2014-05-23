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
	
	SingleWorld world;
	
	GameUI gui;
	PauseUI pui;
	
	public GameScreen (GalaxyWar game) {
		super(game);
	}
	
	@Override
	public void show() {
		
		super.show();
		
		media.loadJson("json/gameData.json");
		
		world = new SingleWorld(media);
		gui = new GameUI(world, media, this);
		pui = new PauseUI(world, media, this);
		
		stage.addActor(world);
		stage.addActor(gui);
		
		world.run();
	}
	
	public void backToMain() {
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
	}
	
	@Override
	public boolean keyDown(int key) {
		
		if (key == Keys.BACK) {
			
			if (world.getState() == WorldState.RUN){
				world.pause();
				showPause();
			
			} else if (world.getState() == WorldState.PAUSE) {
				world.resume();
				showGame();
			}
		}
		
		return true;
	}
}
