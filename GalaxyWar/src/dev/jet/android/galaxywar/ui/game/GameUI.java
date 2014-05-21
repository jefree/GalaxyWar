package dev.jet.android.galaxywar.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.ui.BasicUI;
import dev.jet.android.galaxywar.utils.ScreenUtil;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.BaseWorld.WorldState;
import dev.jet.android.galaxywar.world.single.SingleWorld;

public class GameUI extends BasicUI {
	
	ImageButton bRight;
	ImageButton bLeft;
	
	ImageButton bMissile1;
	ImageButton bMissile2;
	
	MissileBar mBar;
	ShieldBar sBar;
	
	Label score;
	Label message;
	
	SingleWorld world;
	GameScreen screen;
	
	public GameUI(BaseWorld world, Media media, GameScreen screen) {
		
		super(media);
		
		this.world = (SingleWorld) world;
		this.screen = screen;
		
		bRight = (ImageButton)BasicUI.createButton(media, "bArrow", "", "");
		bLeft = (ImageButton)BasicUI.createButton(media, "bArrow", "", "");
		
		bMissile1 = (ImageButton)BasicUI.createButton(media, "bMissile", "", "");
		bMissile2 = (ImageButton)BasicUI.createButton(media, "bMissile", "", "");
		
		mBar = new MissileBar(media);
		sBar = new ShieldBar(media);
		
		score = new Label("0", new LabelStyle(media.getFont("fonts/AmazDoom"),
				new Color(1,1,1,1)));
		
		message = new Label("Eve One - Beta", new LabelStyle(media.getFont("fonts/Comic Sans MS"), 
				new Color(0,0,0,1)));
		
		Table wrapper = new Table();
		wrapper.add(bLeft);
		wrapper.setTransform(true);
		wrapper.setSize(bLeft.getWidth(), bLeft.getHeight());
		wrapper.setOrigin(wrapper.getPrefWidth()/2, wrapper.getPrefHeight()/2);
		wrapper.rotate(180);
		
		score.setFontScale(1.5f);
		score.setAlignment(Align.center);
		
		wrapper.setPosition(25, 5);
		bRight.setPosition(media.getScreenWidth() - bRight.getWidth() - 25, 5);
		
		bMissile1.setPosition(media.getScreenWidth()/2 + 140, 5);
		bMissile2.setPosition(media.getScreenWidth()/2 - bMissile2.getWidth() - 140, 5);
		
		mBar.setPosition(media.getScreenWidth() - mBar.getWidth() - 25, media.getScreenHeight() - mBar.getHeight() - 5);
		sBar.setPosition(25, media.getScreenHeight() - mBar.getHeight() - 5);
		
		ScreenUtil.centerTop(score, this, 0, -60);
		ScreenUtil.top(message, this, 45, -40);
		
		addActor(bRight);
		addActor(wrapper);
		addActor(bMissile2);
		addActor(bMissile1);
		addActor(mBar);
		addActor(sBar);
		addActor(message);
		addActor(score);
	}
	
	@Override
	public void act(float delta) {
		
		if (world.getState() == WorldState.PAUSE) {
			return;
		}
		
		if (world.getState() == WorldState.END) {
			screen.showEnd();
		}
		
		int mShip = (int) world.getShip().getMissiles();
		int sShip = (int) world.getShield().getLife();
		
		if (mShip <= 3) {
			mShip *= -1;
		}
		
		mBar.setScore(mShip);
		sBar.setScore(sShip);
		
		score.setText( Integer.toString(world.getScore()) );
		
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
		
		if (a == bMissile1 || a == bMissile2) {
			world.getShip().shoot();
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
			
			edge = media.getTextureRegion("missilesbar/edge").getTexture();
			normal = media.getTextureRegion("missilesbar/normal").getTexture();
			warning = media.getTextureRegion("missilesbar/warning").getTexture();
			
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
			
			edge = media.getTextureRegion("shieldbar/edge").getTexture();
			shield = media.getTextureRegion("shieldbar/shield").getTexture();
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

	@Override
	protected void init(Table table) {
		// TODO Auto-generated method stub
		
	}
}
