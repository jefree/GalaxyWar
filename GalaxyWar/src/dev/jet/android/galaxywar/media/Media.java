package dev.jet.android.galaxywar.media;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Media {

	HashMap<String, Picture> pictures;
	HashMap<String, BitmapFont> fonts;
	
	public Media() {
		pictures = new HashMap<String, Picture>();
		fonts = new HashMap<String, BitmapFont>();
	}
	
	public int getScreenWidth() {
		return Gdx.graphics.getWidth();
	}
	
	public int getScreenHeight() {
		return Gdx.graphics.getHeight();
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
	
	public BitmapFont getFont(String key) {
		return fonts.get(key);
	}
	
	public Picture getPicture(String key) {
		return pictures.get(key);
	}
}
