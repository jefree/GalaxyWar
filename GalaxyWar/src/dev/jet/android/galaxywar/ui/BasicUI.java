package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dev.jet.android.galaxywar.media.Media;

public abstract class BasicUI extends Group {
	
	protected Media media;
	private Table table;
	
	public BasicUI(Media media) {
		
		this.media = media;
		this.table = new Table();
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		init(this.table);
		
		this.addActor(table);
	}
	
	protected abstract void init(Table table);
	
	public static ImageButton createButton(Media media, String name) {
		
		ImageButtonStyle ibs = new ImageButtonStyle();
		
		ibs.up = new TextureRegionDrawable(media.getTextureRegion(name+"Up"));
		ibs.down = new TextureRegionDrawable(media.getTextureRegion(name+"Down"));
		
		return new ImageButton(ibs);
	}
	
	public static ImageTextButton createButton(Media media, String name, String text, String font) {
		
		ImageTextButtonStyle itbs = new ImageTextButtonStyle();
		
		itbs.up = new TextureRegionDrawable(media.getTextureRegion(name+"Up"));
		itbs.down = new TextureRegionDrawable(media.getTextureRegion(name+"Down"));
		itbs.font = media.getFont(font);
		
		ImageTextButton btn = new ImageTextButton(text, itbs);
		btn.getLabel().setTouchable(Touchable.disabled);
		
		return btn;
	}
}
