package dev.jet.android.galaxywar.media;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class Media {

	HashMap<String, Picture> pictures;
	
	public Media() {
		pictures = new HashMap<String, Picture>();
	}
	
	public int getScreenWidth() {
		return Gdx.graphics.getWidth();
	}
	
	public int getScreenHeight() {
		return Gdx.graphics.getHeight();
	}
	
	private Picture loadPicture(Class<?> cls, String key, String fileName) {
		
		Picture pic = null;
		
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
	
	public void loadSinglePicture(String key, String fileName) {
		loadPicture(SinglePicture.class, key, fileName);
	}
	
	public void loadTapestryPicture(String key, String fileName) {
		TapestryPicture pic = (TapestryPicture) loadPicture(TapestryPicture.class, key, fileName);
		pic.setScreenSize(getScreenWidth(), getScreenHeight());
	}
	
	public Picture getPicture(String key) {
		return pictures.get(key);
	}
}
