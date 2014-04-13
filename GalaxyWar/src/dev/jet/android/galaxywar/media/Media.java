package dev.jet.android.galaxywar.media;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Media {

	HashMap<String, Picture> pictures;
	HashMap<String, BitmapFont> fonts;
	HashMap<String, Sound> sounds;
	HashMap<String, Music> musics;
	
	int screenWidth;
	int screenHeight;
	
	public Media(int w, int h) {
		
		pictures = new HashMap<String, Picture>();
		fonts = new HashMap<String, BitmapFont>();
		sounds = new HashMap<String, Sound>();
		musics = new HashMap<String, Music>();
		
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
			
			pictures.put(key, pic);
			
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
		
		sounds.put(key, sound);
	}
	
	public void loadMusic(String filename) {
		String key = getKeyByFilename(filename);
		Music music = Gdx.audio.newMusic(Gdx.files.internal(filename));
		
		musics.put(key, music);
	}
	
	public void loadFont(String path) {
		BitmapFont font = new BitmapFont(Gdx.files.internal(path+"/font.fnt"), 
				Gdx.files.internal(path+"/font.png"), false);
		
		fonts.put(path, font);
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
		return pictures.get(key);
	}
	
	public Sound getSound(String key) {
		return sounds.get(key);
	}
	public Music getMusic(String key) {
		return musics.get(key);
	}
	
	public BitmapFont getFont(String key) {
		return fonts.get(key);
	}
	
	public void dispose() {
		
		for (Picture pic : pictures.values()) {
			pic.dispose();
		}
		
		for (BitmapFont font : fonts.values()) {
			font.dispose();
		}
		
		for (Music music : musics.values()) {
			music.dispose();
		}
		
		for (Sound sound : sounds.values()) {
			sound.dispose();
		}
		
	}
}
