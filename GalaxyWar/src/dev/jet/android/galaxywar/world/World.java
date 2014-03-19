package dev.jet.android.galaxywar.world;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.media.TapestryPicture;
import dev.jet.android.galaxywar.world.actors.Asteroid;
import dev.jet.android.galaxywar.world.actors.Entity;
import dev.jet.android.galaxywar.world.actors.Missile;
import dev.jet.android.galaxywar.world.actors.Ship;

public class World extends Group {
	
	private Ship ship;
	private MissileController missiles;
	
	private AsteroidsController asteroids;
	
	private float offsetX;
	private float offsetY;
	
	BitmapFont font;
	
	Entity back;
	TapestryPicture background;
	
	public World (Media media) {
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		offsetX = getWidth()/2;
		offsetY = getHeight()/2;
		
		ship = new Ship();
		ship.create(this, media.getPicture("ship"));
		
		asteroids = new AsteroidsController(this, media);
		missiles = new MissileController(this, media);	
		
		back = new Background();
		back.create(this, media.getPicture("background"));
		
		addActor(back);
		addActor(ship);
		addActor(asteroids);	
		addActor(missiles);
		
		font = new BitmapFont();
	}
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		offsetX = getWidth()/2 - ship.getX();
		offsetY = getHeight()/2 - ship.getY();
	}
	
	public void shot() {
		missiles.genNew();
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public float getOffsetX() {
		return offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public Asteroid[] getAsteroids() {
		return asteroids.getGroup();
	}

	public Missile[] getMissiles() {
		return missiles.getGroup();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		
		font.draw(batch, (int)ship.getX() + "    " + (int)ship.getY() , 600, 300);
	}
}
