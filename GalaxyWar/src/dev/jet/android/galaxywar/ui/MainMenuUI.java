package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import dev.jet.android.galaxywar.main.screens.MainScreen;
import dev.jet.android.galaxywar.media.Media;

public class MainMenuUI extends BasicUI {

	Image back;
	
	Label title;
	
	ImageTextButton single;
	ImageTextButton multi;
	ImageTextButton options;
	
	Music music;
	
	MainScreen main;
	
	public MainMenuUI(Media media, MainScreen main) {
		super(media);
		
		this.main = main;
		
		music = media.getMusic("sounds/main");
		music.setLooping(true);
		music.play();
	}
	
	@Override
	protected void init(Table table) {
		
		back = new Image(media.getTextureRegion("main/back"));

		title = new Label("galaxy war", new LabelStyle(media.getFont("fonts/Complex"),
				new Color(1,1,1,1)));
		
		single = BasicUI.createButton(media, "buttons/green", "Single Game", "fonts/AmazDoom");
		multi = BasicUI.createButton(media, "buttons/blue", "Multiplayer", "fonts/AmazDoom");
		options = BasicUI.createButton(media, "buttons/black", "Options", "fonts/AmazDoom");
		
		single.addCaptureListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				main.launchSingleGame();
			}
		});
			
		addActor(back);
		
		//table.debug();
		
		table.setFillParent(true);
		table.add(title).expandY();
		
		table.row();
		table.add(single).right().padBottom(10);
		
		table.row();
		table.add(multi).right().padBottom(10);
		
		table.row();
		table.add(options).right();
	}
}
