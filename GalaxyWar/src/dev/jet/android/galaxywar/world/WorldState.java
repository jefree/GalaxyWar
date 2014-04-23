package dev.jet.android.galaxywar.world;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class WorldState {

	public static int RUN = 0;
	public static int PAUSE = 1;
	public static int STOP = 2;
	public static int END = 3;
	
	public int state;
	
	protected Skin states;
	
	public WorldState() {
		states = new Skin();
	}
	
	public <T> T getState(String key, Class<T> type) {
		return states.get(key, type);
	}
}
