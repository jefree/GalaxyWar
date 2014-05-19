package dev.jet.android.galaxywar.world;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.actors.Entity;
import dev.jet.android.galaxywar.world.actors.Explosion;
import dev.jet.android.galaxywar.world.actors.Shield;
import dev.jet.android.galaxywar.world.actors.Ship;
import dev.jet.android.galaxywar.world.single.ExplosionSingleGroup;
import dev.jet.android.galaxywar.world.single.AsteroidSingle;
import dev.jet.android.galaxywar.world.single.AsteroidSingleGroup;
import dev.jet.android.galaxywar.world.single.MissileSingleGroup;
import dev.jet.android.galaxywar.world.single.MissileSingle;
import dev.jet.android.galaxywar.world.single.ShipShield;

public abstract class BaseWorld extends Group {
	
	public enum WorldState { RUN, PAUSE, STOP, END };
	
	protected WorldState status;
	private HashMap<String, EntityState> states;
	
	protected Ship ship;
	protected ShipShield shield;
	protected Explosion shipExplosion;
	
	protected MissileSingleGroup missiles;
	protected AsteroidSingleGroup asteroids;
	protected ExplosionSingleGroup explosions;
	
	protected Background back;
	protected Music music;
	
	protected float offsetX;
	protected float offsetY;
	
	protected abstract void setActions();
	
	public BaseWorld (Media media) {
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		offsetX = getWidth()/2;
		offsetY = getHeight()/2;
		
		status = WorldState.RUN;
		
		states = new HashMap<String, EntityState>();
		
		ship = new Ship();
		ship.create(this, media.getTextureRegion("ship"));
		
		shield = new ShipShield();
		shield.create(this, media.getTextureRegion("shield"));
		shield.setDefended(ship);
		
		shipExplosion =  new Explosion();
		shipExplosion.create(this, media.getSound("sounds/explosionShip"));
		shipExplosion.setAnimData(media.getTextureAtlas("explosion/ship/anim"), 3);
		
		asteroids = new AsteroidSingleGroup(this, media);
		missiles = new MissileSingleGroup(this, media);
		explosions = new ExplosionSingleGroup(this, media);
		
		back = new Background();
		back.create(this, media.getTextureRegion("space"));
		back.setScreenSize(media.getScreenWidth(), media.getScreenHeight());
		
		music = media.getMusic("sounds/music");
		music.setLooping(true); 
		
		setActions();
		
		addActor(back);
		addActor(ship);
		addActor(shield);
		
		addActor(asteroids);	
		addActor(missiles);
		addActor(explosions);
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void pause() {
		status = WorldState.PAUSE;
		
		music.pause();
	}
	
	public void run() {
		status = WorldState.RUN;
		
		music.setVolume(0.5f);
		music.play();
	}
	
	public void shot() {
		
		if (ship.getLife() > 0 && ship.getMissiles() > 0) {
			missiles.create();
			ship.deltaMissiles(-1);
		}
	}
	
	public void reset() {
		
		ship.reset();
		shield.reset();
		asteroids.reset();
		missiles.reset();
		shipExplosion.reset();
		
		addActor(ship);
		addActor(shield);
		
		music.stop();
		
		run();
	}
	
	public void explosion(Entity e, Entity a) {
		
		Explosion ex = explosions.create();
		Vector2 pos = GeomUtil.midPoint(a.getCenter(), e.getCenter());
		
		ex.setPosition(pos.x, pos.y);
		ex.setRotation(a.getRotation());
	}
	
	public void addState(String key, EntityState state) {
		states.put(key, state);
	}
	
	public EntityState getState(String key) {
		return states.get(key);
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

	public ArrayList<AsteroidSingle> getAsteroids() {
		return asteroids.getEnabledEntities();
	}

	public ArrayList<MissileSingle> getMissiles() {
		return missiles.getEnabledEntities();
	}
	
	public WorldState getState() {
		return status;
	}
}
