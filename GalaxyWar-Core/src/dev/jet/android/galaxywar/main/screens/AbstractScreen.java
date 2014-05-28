package dev.jet.android.galaxywar.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.media.Media;

public class AbstractScreen implements Screen {
	
	protected Stage stage;
	protected GalaxyWar game;
	protected Media media;

	private float actTime = 0.015625f;
	private float time;
	
	public AbstractScreen (GalaxyWar game) {
		this.game = game;
		this.media = game.getMedia();
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void render(float delta) {
		
		time += delta;
		
		if (time >= actTime) {
			
			stage.act(delta);
	        time = 0;
		}
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
        Table.drawDebug(stage);
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(media.getScreenWidth(), media.getScreenHeight());
	}
	
	@Override
	public void show() {

		ScreenViewport viewport = new ScreenViewport();
		viewport.setWorldSize(media.getScreenWidth(), media.getScreenHeight());
		
		stage = new Stage(viewport);
		
		Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		media.dispose();
		
	}

	@Override
	public void hide() {
		dispose();
		
	}	
}
