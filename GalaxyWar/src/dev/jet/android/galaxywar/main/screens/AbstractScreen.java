package dev.jet.android.galaxywar.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import dev.jet.android.galaxywar.main.GalaxyWar;
import dev.jet.android.galaxywar.media.Media;

public class AbstractScreen implements Screen, InputProcessor {
	
	protected Stage stage;
	protected GalaxyWar game;
	protected Media media;
	
	public AbstractScreen (GalaxyWar game) {
		this.game = game;
		this.media = game.getMedia();
		
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
		
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
		
		InputMultiplexer mux = new InputMultiplexer(this, stage);
		
		Gdx.input.setInputProcessor(mux);
		
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
	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
