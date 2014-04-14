package dev.jet.android.galaxywar.media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Media {
	
	public Skin resources;
	
	int screenWidth;
	int screenHeight;
	
	public Media(int w, int h) {
		
		resources = new Skin();
		
		screenWidth = w;
		screenHeight = h;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	private Picture loadPicture(Class<?> cls, String fileName) {
		
		Picture pic = null;
		
		String key = getKeyByFilename(fileName);
		
		try {
			
			pic = (Picture)cls.newInstance();
			pic.load(fileName);
			
			resources.add(key, pic, Picture.class);
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pic;
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
	
	public void loadAnimPicture(String fileName) {
		loadPicture(AnimationPicture.class, fileName);
	}
	
	public void loadSinglePicture(String fileName) {
		loadPicture(SinglePicture.class, fileName);
	}
	
	public void loadTapestryPicture(String fileName) {
		TapestryPicture pic = (TapestryPicture) loadPicture(TapestryPicture.class, fileName);
		pic.setScreenSize(getScreenWidth(), getScreenHeight());
	}
	
	public void loadAlphaLifePicture(String fileName){
		loadPicture(AlphaLifePicture.class, fileName);
	}
	
	private String getKeyByFilename(String filename) {
		
		String[] split = filename.split("\\.");
		String key = "";
		
		for (int i=0; i<split.length-1; i++) {
			
			key = key.concat(split[i]);
		}
		
		return key;
		
	}
	
	public Picture getPicture(String key) {
		return resources.get(key, Picture.class);
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
}
