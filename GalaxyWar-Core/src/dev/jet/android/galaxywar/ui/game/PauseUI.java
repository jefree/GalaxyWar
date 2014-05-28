package dev.jet.android.galaxywar.ui.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.ui.BasicUI;
import dev.jet.android.galaxywar.world.BaseWorld;

public class PauseUI extends EndUI {
	
	Actor run;
	
	public PauseUI(BaseWorld world, Media media, GameScreen screen) {
		super(world, media, screen);
		
		title.setText("Pausado");
		
	}
	
	@Override
	protected void init(Table table) {
		super.init(table);
		
		run = BasicUI.createButton(media, "buttons/blue", "Volver al Juego", "fonts/Comic Sans MS");
		
		run.addCaptureListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				world.resume();
				screen.showGame();
			}
		});
		
		table.clear();
		
		table.add(title).padBottom(50);
		
		table.row();
		table.add(run).padBottom(20);
		
		table.row();
		table.add(again).padBottom(20);
		
		table.row();
		table.add(exit);
	}
}
