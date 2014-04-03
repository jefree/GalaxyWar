package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.World;

public class GameUI extends BasicUI {
	
	ImageButton bRight;
	ImageButton bLeft;
	
	ImageButton bMissile;
	ImageButton bSpeed;
	
	MissileBar mBar;
	ShieldBar sBar;
	
	World world;
	GameScreen screen;
	
	public GameUI(World _world, Media _media, GameScreen _screen) {
		
		super(_media);
		
		world = _world;
		screen = _screen;
		
		bRight = (ImageButton)BasicUI.createButton(media, "bArrow", "", "");
		bLeft = (ImageButton)BasicUI.createButton(media, "bArrow", "", "");
		
		bMissile = (ImageButton)BasicUI.createButton(media, "bMissile", "", "");
		bSpeed = (ImageButton)BasicUI.createButton(media, "bSpeed", "", "");
		
		mBar = new MissileBar(media);
		sBar = new ShieldBar(media);
		
		Table wrapper = new Table();
		wrapper.add(bLeft);
		wrapper.setTransform(true);
		wrapper.setSize(bLeft.getWidth(), bLeft.getHeight());
		wrapper.setOrigin(wrapper.getPrefWidth()/2, wrapper.getPrefHeight()/2);
		wrapper.rotate(180);
		
		wrapper.setPosition(25, 5);
		bRight.setPosition(media.getScreenWidth() - bRight.getWidth() - 25, 5);
		
		bMissile.setPosition(media.getScreenWidth()/2 + 80, 5);
		bSpeed.setPosition(media.getScreenWidth()/2 - bSpeed.getWidth() - 80, 5);
		
		mBar.setPosition(media.getScreenWidth() - mBar.getWidth() - 25, media.getScreenHeight() - mBar.getHeight() - 5);
		sBar.setPosition(25, media.getScreenHeight() - mBar.getHeight() - 5);
		
		addActor(bRight);
		addActor(wrapper);
		addActor(bSpeed);
		addActor(bMissile);
		addActor(mBar);
		addActor(sBar);
	}
	
	@Override
	public void act(float delta) {
		
		if (world.getState() == World.PAUSE) {
			return;
		}
		
		if (world.getState() == World.END) {
			screen.showEnd();
		}
		
		int mShip = (int) world.getShip().getMissiles();
		int sShip = (int) world.getShield().getLife();
		
		if (mShip <= 3) {
			mShip *= -1;
		}
		
		mBar.setScore(mShip);
		sBar.setScore(sShip);
		
		if (bRight.isPressed()) {
			world.getShip().setRotParameter(-1);
		}
		
		if (bLeft.isPressed()) {
			world.getShip().setRotParameter(1);
		}
	}
	
	
	@Override
	public void onTouchDown(float x, float y) {
		
		Actor a = hit(x, y, true);
		
		if (a == bMissile) {
			world.shot();
		}
		
	}

	@Override
	public void onTouchUp(float x, float y) {
	}
	
	/**
	 * A MissileBar represents graphically the amount of missiles that 
	 * the player had currently.
	 * 
	 * @author jefferson
	 *
	 */
	class MissileBar extends Image {
		
		Texture edge;
		Texture normal;
		Texture warning;
		
		int score;
		
		public MissileBar (Media media) {
			
			edge = media.getPicture("missilesbar/edge").getTexture();
			normal = media.getPicture("missilesbar/normal").getTexture();
			warning = media.getPicture("missilesbar/warning").getTexture();
			
			setWidth(edge.getWidth());
			setHeight(edge.getHeight());
		}
		
		public void setScore(int _score) {
			score = _score;
		}
		
		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			
			super.draw(batch, parentAlpha);
			
			Texture state = (score > 0) ? normal : warning;

			batch.draw(edge, getX(), getY());
			
			for(int i=0; i < Math.abs(score); i++){
				batch.draw(state, getX() + i*(state.getWidth()+2) + 8, getY() + 8.1f);
			}
			
		}
		
	}
	
	/**
	 * A ShielBar represents graphically the life of a Ship.
	 * 
	 * @author jefferson
	 *
	 */
	class ShieldBar extends Image {
		
		Texture edge;
		Texture shield;
		
		int score;
		
		public ShieldBar(Media media) {
			
			edge = media.getPicture("shieldbar/edge").getTexture();
			shield = media.getPicture("shieldbar/shield").getTexture();
			score = 100;
			
			setWidth(shield.getWidth());
			setHeight(shield.getHeight());
		}
		
		public void setScore(int _score) {
			score = _score;
		}
		
		public void draw(SpriteBatch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			
			batch.draw(edge, getX(), getY());
			batch.draw(shield, getX() + 8, getY() + 7, (int)(shield.getWidth() * score/100.0), shield.getHeight());
			
		}
		
	}
}
