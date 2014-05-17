package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dev.jet.android.galaxywar.media.Media;

public abstract class BasicUI extends Group {
	
	protected Media media;
	
	public BasicUI(Media _media) {
		
		media = _media;
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		addListener(new BasicUIListener());
	}
	
	public abstract void onTouchDown(float x, float y);
	public abstract void onTouchUp(float x, float y);
	
	public static Actor createButton(Media media, String name, String text, String font) {
		
		Actor button = null;
		
		if (text.equals("")){
			ImageButtonStyle ibs = new ImageButtonStyle();
			
			ibs.up = new TextureRegionDrawable(media.getTextureRegion(name+"Up"));
			ibs.down = new TextureRegionDrawable(media.getTextureRegion(name+"Down"));
			
			button = new ImageButton(ibs);
		
		} else {
			
			ImageTextButtonStyle itbs = new ImageTextButtonStyle();
			
			itbs.up = new TextureRegionDrawable(media.getTextureRegion(name+"Up"));
			itbs.down = new TextureRegionDrawable(media.getTextureRegion(name+"Down"));
			itbs.font = media.getFont(font);
			
			ImageTextButton aux = new ImageTextButton(text, itbs);
			
			aux.getLabel().setTouchable(Touchable.disabled);
			
			button = aux;
			
		}
		
		return button;
		
	}
	
	class BasicUIListener extends InputListener {
		
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			onTouchDown(x, y);
			
			return true;
		}
		
		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			// TODO Auto-generated method stub
			onTouchUp(x, y);
		}
		
	}

}
