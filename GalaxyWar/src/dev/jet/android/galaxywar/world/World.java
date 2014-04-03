package dev.jet.android.galaxywar.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.media.TapestryPicture;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.actors.Asteroid;
import dev.jet.android.galaxywar.world.actors.Entity;
import dev.jet.android.galaxywar.world.actors.Explosion;
import dev.jet.android.galaxywar.world.actors.Missile;
import dev.jet.android.galaxywar.world.actors.Shield;
import dev.jet.android.galaxywar.world.actors.Ship;

public class World extends Group {
	
	public static int RUN = 0;
	public static int PAUSE = 1;
	public static int STOP = 2;
	public static int END = 3;
	
	private Ship ship;
	private Shield shield;
	private Explosion shipExplosion;
	
	private MissileController missiles;
	private AsteroidsController asteroids;
	private AstExplosionController explosions;
	
	private float offsetX;
	private float offsetY;
	
	private int state;
	
	Label message;
	
	Entity back;
	TapestryPicture background;
	
	public World (Media media) {
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		offsetX = getWidth()/2;
		offsetY = getHeight()/2;
		
		ship = new Ship();
		ship.create(this, media.getPicture("ship"));
		
		shield = new Shield();
		shield.create(this, media.getPicture("shield"));
		shield.setDefended(ship);
		
		shipExplosion =  new Explosion();
		shipExplosion.create(this, media.getPicture("explosion/ship/anim"));
		shipExplosion.setDuration(3);
		
		asteroids = new AsteroidsController(this, media);
		missiles = new MissileController(this, media);	
		explosions = new AstExplosionController(this, media);
		
		back = new Background();
		back.create(this, media.getPicture("space"));
		
		message = new Label("Version Beta 1.0 - Eve One", new LabelStyle(media.getFont("fonts/Comic Sans MS"), 
				new Color(1,1,1,1)));
		
		message.setPosition(300, getHeight()-30);
		
		addActor(back);
		addActor(ship);
		addActor(shield);
		
		addActor(asteroids);	
		addActor(missiles);
		addActor(explosions);
		
		addActor(message);
		
	}
	
	@Override
	public void act(float delta) {
		
		if (state != PAUSE) {
			super.act(delta);
		}
		
		if (state==RUN && ship.getLife() < 0) {
			state = STOP;
			addActor(shipExplosion);
			ship.remove();
			shield.remove();
		}
		
		if(state==STOP) { 
			
			shipExplosion.setPosition(ship.getX(), ship.getY());
			
			if(shipExplosion.isFinished()) {
				state = END;
			}
		}
		
		offsetX = getWidth()/2 - ship.getX();
		offsetY = getHeight()/2 - ship.getY();
	}

	public void pause() {
		state = PAUSE;
	}
	
	public void run() {
		state = RUN;
	}
	
	public void reboot() {
		
		ship.reboot();
		asteroids.reboot();
		missiles.reboot();
		shipExplosion.reboot();
		
		addActor(ship);
		addActor(shield);
		
		run();
	}
	
	public void explosion(Entity e, Entity a) {
		
		Explosion ex = explosions.genNew();
		Vector2 pos = GeomUtil.midPoint(e.getCenter(), e.getCenter());
		
		ex.setPosition(pos.x, pos.y);
		ex.setRotation(a.getRotation());
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
	
	public Shield getShield() {
		return shield;
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
	
	public int getState() {
		return state;
	}
}
