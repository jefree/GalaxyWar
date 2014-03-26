package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.World;

public class EndUI extends BasicUI {

	World world;
	GameScreen screen;
	
	Image back;
	ImageButton again;
		
	public EndUI(World _world, Media _media, GameScreen _screen) {
		super(_media);
		
		world = _world;
		screen = _screen;
		
		ImageButtonStyle againStyle = new ImageButtonStyle();
		againStyle.up = new TextureRegionDrawable(media.getPicture("end/againUp").getImage());
		againStyle.down = new TextureRegionDrawable(media.getPicture("end/againDown").getImage());
		
		back = new Image(media.getPicture("end/back").getTexture());
		again = new ImageButton(againStyle);
		
		back.setPosition(getWidth()/2 - back.getWidth()/2, getHeight()/2 - back.getHeight()/2);
		again.setPosition(getWidth()/2 - again.getWidth()/2, getHeight()/2 - again.getHeight()/2 - 100);
		
		addActor(back);
		addActor(again);
	}

	@Override
	public void onTouchDown(float x, float y) {
		
	}

	@Override
	public void onTouchUp(float x, float y) {
		
		Actor a = hit(x, y, false);
		
		if (a == again) {
			world.reboot();
			screen.showGame();
		}
	}

}
