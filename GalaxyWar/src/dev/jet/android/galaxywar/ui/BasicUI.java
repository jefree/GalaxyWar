package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import dev.jet.android.galaxywar.media.Media;

public abstract class BasicUI extends Group {
	Media media;
	
	public BasicUI(Media _media) {
		
		media = _media;
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		addListener(new BasicUIListener());
	}
	
	public abstract void onTouchDown(float x, float y);
	public abstract void onTouchUp(float x, float y);
	
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
