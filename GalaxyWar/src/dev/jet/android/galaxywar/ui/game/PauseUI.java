package dev.jet.android.galaxywar.ui.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.ui.BasicUI;
import dev.jet.android.galaxywar.utils.ScreenUtil;
import dev.jet.android.galaxywar.world.BaseWorld;

public class PauseUI extends EndUI {
	
	Actor run;
	
	public PauseUI(BaseWorld _world, Media _media, GameScreen _screen) {
		super(_world, _media, _screen);
				
		run = BasicUI.createButton(media, "buttons/blue", "Volver al Juego", "fonts/Comic Sans MS");
		
		title.setText("Pausado");
		
		ScreenUtil.centered(run, back, 0, 40);
		
		score.remove();
		
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
