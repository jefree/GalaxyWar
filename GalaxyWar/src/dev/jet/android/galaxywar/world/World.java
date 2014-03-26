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
	
	public static int RUN = 0;
	public static int PAUSE = 1;
	public static int STOP = 2;
	public static int END = 3;
	
	private Ship ship;
	private MissileController missiles;
	
	private AsteroidsController asteroids;
	
	private float offsetX;
	private float offsetY;
	
	private int state;
	
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
		back.create(this, media.getPicture("space"));
		
		addActor(back);
		addActor(ship);
		addActor(asteroids);	
		addActor(missiles);
		
		font = new BitmapFont();
	}
	
	public void reboot() {
		
		ship.reboot();
		asteroids.reboot();
		missiles.reboot();
		
		state = RUN;
	}
	
	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		if (ship.getLife() <= 0) {
			state = STOP;
		}
		
		offsetX = getWidth()/2 - ship.getX();
		offsetY = getHeight()/2 - ship.getY();
	}
	
	public int getState() {
		return state;
	}

	public void shot() {
		
		if (ship.getMissiles() > 0) {
			missiles.genNew();
			ship.deltaMissiles(-1);
		}
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
