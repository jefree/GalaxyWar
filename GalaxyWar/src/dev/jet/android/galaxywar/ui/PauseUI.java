package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.ScreenUtil;
import dev.jet.android.galaxywar.world.World;

public class PauseUI extends EndUI {
	
	Actor run;
	
	public PauseUI(World _world, Media _media, GameScreen _screen) {
		super(_world, _media, _screen);
				
		run = BasicUI.createButton(media, "pause/run", "Volver al Juego", "fonts/Comic Sans MS");
		
		ScreenUtil.centered(run, this, 0, 100);
		ScreenUtil.centered(again, this, 0, 0);
		ScreenUtil.centered(exit, this, 0, -100);
		
		addActor(run);
	}
	
	@Override
	public void onTouchUp(float x, float y) {
		super.onTouchUp(x, y);
		
		Actor a = hit(x, y, true);
		
		System.out.println(a);
		
		if (a == run){
			screen.showGame();
		} 
		
	}
	
}
