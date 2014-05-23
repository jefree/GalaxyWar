package dev.jet.android.galaxywar.media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Media {
	
	public Skin resources;
	
	int screenWidth;
	int screenHeight;
	
	public Media() {
		resources = new Skin();
	}
	
	public void loadJson(String jsonFile) {
		
		JsonValue data = new JsonReader().parse(Gdx.files.internal(jsonFile));
		
		for (JsonValue entry = data.child(); entry != null; entry = entry.next()) {
			
			String[] list = entry.asStringArray();
			
			for (String string : list) {
				
				if (entry.name().equals("texture")) {
					loadTextureRegion(string);
				
				} else if (entry.name().equals("atlas")) {
					loadTextureAtlas(string);
				
				} else if (entry.name().equals("font")) {
					loadFont(string);
					
				} else if (entry.name().equals("sound")) {
					loadSound(string);
					
				} else if (entry.name().equals("music")) {
					loadMusic(string);
				}
			}
		}
		
	}
	
	public void setScreenSize(int w, int h) {
		screenWidth = w;
		screenHeight = h;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public void loadTextureRegion(String filename) {
		resources.add(getKeyByFilename(filename),
				new TextureRegion(new Texture(filename)), 
				TextureRegion.class);
	}
	
	public void loadTextureAtlas(String filename) {
		resources.add(getKeyByFilename(filename), 
				new TextureAtlas(filename),
				TextureAtlas.class);
		
	}
	
	public void loadSound(String filename) {
		String key = getKeyByFilename(filename);
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(filename));
		
		resources.add(key, sound, Sound.class);
	}
	
	public void loadMusic(String filename) {
		String key = getKeyByFilename(filename);
		Music music = Gdx.audio.newMusic(Gdx.files.internal(filename));
		
		resources.add(key, music, Music.class);
	}
	
	public void loadFont(String path) {
		BitmapFont font = new BitmapFont(Gdx.files.internal(path+"/font.fnt"), 
				Gdx.files.internal(path+"/font.png"), false);
		
		resources.add(path, font);
	}
	
	private String getKeyByFilename(String filename) {
		
		String[] split = filename.split("\\.");
		String key = "";
		
		for (int i=0; i<split.length-1; i++) {
			
			key = key.concat(split[i]);
		}
		
		return key;
		
	}
	
	public TextureRegion getTextureRegion(String key) {
		return resources.get(key, TextureRegion.class);
	}
	
	public TextureAtlas getTextureAtlas (String key) {
		return resources.get(key, TextureAtlas.class);
	}
	
	public Sound getSound(String key) {
		return resources.get(key, Sound.class);
	}
	public Music getMusic(String key) {
		return resources.get(key, Music.class);
	}
	
	public BitmapFont getFont(String key) {
		return resources.getFont(key);
	}
	
	public void dispose() {
		resources.dispose();
	}
	
	class MediaSkin extends Skin implements Json.Serializable {

		@Override
		public void read(Json arg0, JsonValue arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void write(Json arg0) {
			// TODO Auto-generated method stub
			
		}}
}
