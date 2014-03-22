package dev.jet.android.galaxywar.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.media.Media;

public class AbstractScreen implements Screen {
	
	protected Stage stage;
	protected GalaxyWar game;
	protected Media media;
	
	public AbstractScreen (GalaxyWar _game) {
		game = _game;
		media = game.getMedia();
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height);
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
	}
	
}
