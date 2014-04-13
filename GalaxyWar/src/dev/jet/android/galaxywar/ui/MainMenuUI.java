package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dev.jet.android.galaxywar.main.screens.MainScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.ScreenUtil;

public class MainMenuUI extends BasicUI {

	Image back;
	
	Label title;
	
	Actor single;
	Actor multi;
	Actor options;
	
	MainScreen main;
	
	public MainMenuUI(Media _media, MainScreen _main) {
		super(_media);
		
		main = _main;
		
		back = new Image(media.getPicture("main/back").getTexture());
		
		title = new Label("galaxy war", new LabelStyle(media.getFont("fonts/Complex"),
				new Color(1,1,1,1)));
		
		single = BasicUI.createButton(_media, "buttons/green", "Single Game", "fonts/AmazDoom");
		multi = BasicUI.createButton(_media, "buttons/blue", "Multiplayer", "fonts/AmazDoom");
		options = BasicUI.createButton(_media, "buttons/black", "Options", "fonts/AmazDoom");
		
		ScreenUtil.centerTop(title, this, 0, -100);
		
		ScreenUtil.right(single, back, -single.getWidth() - 25, 180);
		ScreenUtil.right(multi, back, -multi.getWidth() - 25, 90);
		ScreenUtil.right(options, back, -options.getWidth() - 25, 0);
		
		addActor(back);
		addActor(single);
		addActor(multi);
		addActor(options);
		addActor(title);

	}

	@Override
	public void onTouchDown(float x, float y) {
		
	}

	@Override
	public void onTouchUp(float x, float y) {
		
		Actor a = hit(x, y , true);
		
		if (a == single) {
			main.launchSingleGame();
		}
		
	}
	
}
