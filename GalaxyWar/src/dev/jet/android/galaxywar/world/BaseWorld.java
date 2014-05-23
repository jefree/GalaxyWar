package dev.jet.android.galaxywar.world;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.actors.Explosion;
import dev.jet.android.galaxywar.world.actors.Missile;
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
	
	private Actor focus;
	private Vector2 offset;
	
	public BaseWorld (Media media) {
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		focus = this;
		offset = new Vector2();
		
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
		
		addActor(back);
		addActor(ship);
		addActor(shield);
		
		addActor(asteroids);	
		addActor(missiles);
		addActor(explosions);
		
		//Entity.debug(true);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		offset.x = getWidth()/2 - focus.getX();;
		offset.y = getHeight()/2 - focus.getY();;
	}
	
	public void run() {
		status = WorldState.RUN;
		
		music.setVolume(0.5f);
		music.play();
	}
	
	public void resume() {
		status = WorldState.RUN;
		music.play();
	}
	
	public void pause() {
		status = WorldState.PAUSE;
		music.pause();
	}
	
	public void genMissile(Vector2 position, Vector2 direction) {
		
		Missile missile = missiles.create();
		
		missile.moveBy(position.x, position.y);
		missile.setDirection(direction);
	}
	
	public void genAstExplosion(Vector2 pos) {
		
		Explosion ex = explosions.create();
		ex.setPosition(pos.x, pos.y);
	}
	
	public void setFocusActor(Actor actor) {
		focus = actor;
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
		return offset.x;
	}

	public float getOffsetY() {
		return offset.y;
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
