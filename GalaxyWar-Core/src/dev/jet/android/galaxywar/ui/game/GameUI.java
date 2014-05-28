package dev.jet.android.galaxywar.ui.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import dev.jet.android.galaxywar.main.screens.GameScreen;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.ui.BasicUI;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.BaseWorld.WorldState;
import dev.jet.android.galaxywar.world.single.SingleWorld;

public class GameUI extends BasicUI {
	
	ImageButton bRight;
	ImageButton bLeft;
	
	ImageButton bMissile;
	
	MissileBar mBar;
	ShieldBar sBar;
	
	Label score;
	
	SingleWorld world;
	GameScreen screen;
	
	boolean right, left, shoot;
	
	public GameUI(BaseWorld world, Media media, GameScreen screen) {
		super(media);
		
		this.world = (SingleWorld) world;
		this.screen = screen;
		
		score.setFontScale(1.5f);
		score.setAlignment(Align.center);
		
		mBar.setMaxScore(world.getShip().maxMissiles);
		
		createInitMessage();
	}
	
	private void createInitMessage() {
		
		final Table message = new Table();
		Label label = new Label("Collect All Targets", new LabelStyle(media.getFont("fonts/Comic Sans MS"), Color.WHITE));
		Image icon = new Image(media.getTextureRegion("target"));
		
		label.setFontScale(1.5f);
		
		message.setFillParent(true);
		
		message.add(label);
		message.add(icon).padLeft(15);
		message.pack();
		
		message.addCaptureListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				message.clearActions();
				message.remove();
			}
		});
		
		addActor(message);
		addAction(Actions.sequence(Actions.delay(5), Actions.removeActor(message)));
	}
	
	@Override
	protected void init(Table table) {
		
		bRight = BasicUI.createButton(media, "bArrow");
		bLeft = BasicUI.createButton(media, "bArrow");
		
		bMissile = BasicUI.createButton(media, "bMissile");
		
		bMissile.addCaptureListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				shoot = true;
				return false;
			}
		});
		
		mBar = new MissileBar(media);
		sBar = new ShieldBar(media);
		
		score = new Label("0", new LabelStyle(media.getFont("fonts/AmazDoom"),
				Color.WHITE));
		
		Table wrapper = new Table();
		wrapper.add(bLeft);
		wrapper.setTransform(true);
		wrapper.setSize(bLeft.getWidth(), bLeft.getHeight());
		wrapper.setOrigin(wrapper.getPrefWidth()/2, wrapper.getPrefHeight()/2);
		wrapper.rotateBy(180);
		wrapper.pack();
		
		//table.debug();
		
		table.setFillParent(true);
		table.pad(25);
		
		table.row().expandY().top();
		table.add(sBar).left().colspan(2);
		table.add(score).expandX().spaceRight(70);
		table.add(mBar).right();
		
		table.row().expandY().bottom();
		
		table.add(wrapper).right().padRight(10);
		table.add(bRight).left();
		table.add(bMissile).right().colspan(2);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (world.getState() == WorldState.PAUSE) {
			return;
		}
		
		if (world.getState() == WorldState.END) {
			screen.showEnd();
			return;
		}
		
		int mShip = (int) world.getShip().getMissiles();
		int sShip = (int) world.getShield().life;
		
		mBar.setScore(mShip);
		sBar.setScore(sShip);
		
		score.setText( Integer.toString(world.getScore()) );
		
		if (bRight.isPressed() | right) {
			world.getShip().setRotParameter(-1);
		}
		
		if (bLeft.isPressed() | left) {
			world.getShip().setRotParameter(1);
		}
		
		if (shoot) {
			world.getShip().shoot();
			shoot = false;
		}
	}
	
	@Override
	protected void onKeyDown(int keycode) {

		if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
			
			if (world.getState() == WorldState.RUN){
				world.pause();
				screen.showPause();
			
			}
		}
		
		if (keycode == Keys.RIGHT) {
			right = true;
		}
		
		if (keycode == Keys.LEFT) {
			left = true;
		}
		
		if (keycode == Keys.SPACE) {
			shoot = true;
		}
	}

	@Override
	protected void onKeyUp(int keycode) {
		
		if (keycode == Keys.RIGHT) {
			right = false;
		}
		
		if (keycode == Keys.LEFT) {
			left = false;
		}
		
	}
	
	/**
	 * A MissileBar represents graphically the amount of missiles that 
	 * the player had currently.
	 * 
	 * @author jefferson
	 *
	 */
	class MissileBar extends Widget {
		
		final int MISSILE_BAR_WIDTH = 103;
		
		float missileWidth;
		float missilePad;
		
		Texture edge;
		Texture normal;
		Texture warning;
		
		int warnScore;
		int maxScore;
		int score;
		
		public MissileBar (Media media) {
			
			edge = media.getTextureRegion("missilesbar/edge").getTexture();
			normal = media.getTextureRegion("missilesbar/normal").getTexture();
			warning = media.getTextureRegion("missilesbar/warning").getTexture();
		}
		
		public void setMaxScore(int score) {
			
			maxScore = score;
			warnScore = (int) (score * 0.5);
			
			missilePad = MISSILE_BAR_WIDTH / (7.5f * maxScore);
			missileWidth = missilePad * 6.5f;
			
			System.out.println(missileWidth + " " + missilePad);
			
		}
		
		public void setScore(int score) {
			this.score = score >= 0 ? score : 0;
		}

		@Override
		public float getPrefWidth() {
			return edge.getWidth();
		}
		
		@Override
		public float getPrefHeight() {
			return edge.getHeight();
		}
		
		@Override
		public void draw(Batch batch, float parentAlpha) {
			
			super.draw(batch, parentAlpha);
			
			Texture state = (score > warnScore) ? normal : warning;

			batch.draw(edge, getX(), getY());
			
			float deltaX = getX() + missilePad/2 + 8;
			float deltaY = getY() + 8.1f;
			
			for(int i=0; i < score; i++){
				batch.draw(state, deltaX + i*(missileWidth+missilePad), deltaY, //set x and y
						getOriginX(), getOriginY(),
						missileWidth, normal.getHeight(), // set scale on x
						getScaleX(), getScaleY(),
						getRotation(), 
						0, 0,	//draw from image begin
						normal.getWidth(), normal.getHeight(), //draw all image
						false, false);
			}
			
		}
		
	}
	
	/**
	 * A ShielBar represents graphically the life of a Ship.
	 * 
	 * @author jefferson
	 *
	 */
	class ShieldBar extends Widget {
		
		Texture edge;
		Texture shield;
		
		int score;
		
		public ShieldBar(Media media) {
			
			edge = media.getTextureRegion("shieldbar/edge").getTexture();
			shield = media.getTextureRegion("shieldbar/shield").getTexture();
			score = 100;
		}
		
		public void setScore(int _score) {
			score = _score;
		}
		
		@Override
		public float getPrefWidth() {
			return edge.getWidth();
		}
		
		@Override
		public float getPrefHeight() {
			return edge.getHeight();
		}
		
		@Override
		public void draw(Batch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			
			batch.draw(edge, getX(), getY());
			batch.draw(shield, getX() + 8, getY() + 7, (int)(shield.getWidth() * score/100.0), shield.getHeight());
			
		}
		
	}
}
