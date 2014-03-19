package dev.jet.android.galaxywar.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.World;

public class GameUI extends BasicUI {

	ImageButton bRight;
	ImageButton bLeft;
	ImageButton bMissile;
	ImageButton bSpeed;
	
	World world;
	
	public GameUI(World _world, Media _media) {
		
		super(_media);
		
		world = _world;
		
		ImageButtonStyle bDirStyle = new ImageButtonStyle();
		bDirStyle.up = new TextureRegionDrawable(media.getPicture("bArrowUp").getImage());
		bDirStyle.down = new TextureRegionDrawable(media.getPicture("bArrowDown").getImage());
		
		ImageButtonStyle bMissileStyle = new ImageButtonStyle();
		bMissileStyle.up = new TextureRegionDrawable(media.getPicture("bMissileUp").getImage());
		bMissileStyle.down = new TextureRegionDrawable(media.getPicture("bMissileDown").getImage());
		
		ImageButtonStyle bSpeedStyle = new ImageButtonStyle();
		bSpeedStyle.up = new TextureRegionDrawable(media.getPicture("bSpeedUp").getImage());
		bSpeedStyle.down = new TextureRegionDrawable(media.getPicture("bSpeedDown").getImage());
		
		bRight = new ImageButton(bDirStyle);
		bLeft = new ImageButton(bDirStyle);
		bMissile = new ImageButton(bMissileStyle);
		bSpeed = new ImageButton(bSpeedStyle);
		
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
		
		addActor(bRight);
		addActor(wrapper);
		addActor(bSpeed);
		addActor(bMissile);

	}
	
	@Override
	public void act(float delta) {
		
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
}
