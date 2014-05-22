package dev.jet.android.galaxywar.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.ui.BasicUI;
import dev.jet.android.galaxywar.utils.ScreenUtil;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.single.SingleWorld;

public class EndUI extends BasicUI {

	SingleWorld world;
	GameScreen screen;
	
	Image back;
	
	Actor again;
	Actor exit;
	Label title;
	Label score;
	
	public EndUI(BaseWorld world, Media media, GameScreen screen) {
		
		super(media);
		
		this.world = (SingleWorld) world;
		this.screen = screen;
		
		ScreenUtil.centered(back, this, 0, 0);
		
		score.setText("Score: " + this.world.getScore());
		score.setAlignment(Align.center);
		score.setFontScale(1.5f);
		
		title.setFontScale(2.5f);
		title.setAlignment(Align.center);
	}

	@Override
	protected void init(Table table) {
		
		back = new Image(media.getTextureRegion("end/back"));
		
		again = BasicUI.createButton(media, "buttons/red", "Reintentar", "fonts/Comic Sans MS");
		exit = BasicUI.createButton(media, "buttons/black", "Salir", "fonts/Comic Sans MS");
		title = new Label("GAME OVER", new LabelStyle(media.getFont("fonts/AmazDoom"), new Color(1,1,1,1)));
		score = new Label("", new LabelStyle(media.getFont("fonts/AmazDoom"), new Color (0,0,0,1)));
		
		addActor(back);
		
		table.setFillParent(true);
		table.add(title).padBottom(50);
		
		table.row();
		table.add(score).padBottom(50);
		
		table.row();
		table.add(again).padBottom(20);
		
		table.row();
		table.add(exit);
		
	}

	@Override
	public void onTouchUp(float x, float y) {
		
		Actor a = hit(x, y, true);
		
		if (a == again) {
			world.reset();
			screen.showGame();
		} else if (a == exit) {
			screen.backToMain();
		}
	}
	
	@Override
	public void onTouchDown(float x, float y) {
	
	}
}
