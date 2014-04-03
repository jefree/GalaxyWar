package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.ScreenUtil;
import dev.jet.android.galaxywar.world.World;

public class EndUI extends BasicUI {

	World world;
	GameScreen screen;
	
	Image back;
	
	Actor again;
	Actor exit;
		
	public EndUI(World _world, Media _media, GameScreen _screen) {
		super(_media);
		
		world = _world;
		screen = _screen;
		
		back = new Image(media.getPicture("end/back").getTexture());
		again = BasicUI.createButton(media, "end/again", "Reintentar", "fonts/Comic Sans MS");
		exit = BasicUI.createButton(media, "end/exit", "Salir", "fonts/Comic Sans MS");
		
		ScreenUtil.centered(back, this, 0, 0);
		ScreenUtil.centered(again, this, 0, 60);
		ScreenUtil.centered(exit, this, 0, -60);
		
		addActor(back);
		addActor(again);
		addActor(exit);
	}

	@Override
	public void onTouchDown(float x, float y) {
		
	}

	@Override
	public void onTouchUp(float x, float y) {
		
		Actor a = hit(x, y, true);
		
		if (a == again) {
			world.reboot();
			screen.showGame();
		} else if (a == exit) {
			System.exit(0);
		}
	}

}
