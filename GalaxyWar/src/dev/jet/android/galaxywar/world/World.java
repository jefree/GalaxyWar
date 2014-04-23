package dev.jet.android.galaxywar.world;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.actors.Asteroid;
import dev.jet.android.galaxywar.world.actors.Entity;
import dev.jet.android.galaxywar.world.actors.Explosion;
import dev.jet.android.galaxywar.world.actors.Missile;
import dev.jet.android.galaxywar.world.actors.Shield;
import dev.jet.android.galaxywar.world.actors.Ship;
import dev.jet.android.galaxywar.world.single.AstExplosionController;
import dev.jet.android.galaxywar.world.single.AsteroidsController;
import dev.jet.android.galaxywar.world.single.MissileController;
import dev.jet.android.galaxywar.world.single.ShipShield;
import dev.jet.android.galaxywar.world.single.WorldStateSingle;


public abstract class World extends Group {
	
	protected Ship ship;
	protected ShipShield shield;
	protected Explosion shipExplosion;
	
	protected MissileController missiles;
	protected AsteroidsController asteroids;
	protected AstExplosionController explosions;
	
	protected Entity back;
	protected Music music;
	
	protected WorldState status;
	
	protected float offsetX;
	protected float offsetY;
	
	protected int score;
	
	protected abstract void setTimers();
	
	public World (Media media) {
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		offsetX = getWidth()/2;
		offsetY = getHeight()/2;
		
		ship = new Ship();
		ship.create(this, media.getPicture("ship"), null);
		
		shield = new ShipShield();
		shield.create(this, media.getPicture("shield"), null);
		shield.setDefended(ship);
		
		shipExplosion =  new Explosion();
		shipExplosion.create(this, media.getPicture("explosion/ship/anim"), media.getSound("sounds/explosionShip"));
		shipExplosion.setDuration(3);
		
		asteroids = new AsteroidsController(this, media);
		missiles = new MissileController(this, media);
		explosions = new AstExplosionController(this, media);
		
		back = new Background();
		back.create(this, media.getPicture("space"), null);
		
		music = media.getMusic("sounds/music");
		music.setLooping(true); 
		
		setTimers();
		
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
		status.state = WorldStateSingle.PAUSE;
		
		music.pause();
	}
	
	public void run() {
		status.state = WorldStateSingle.RUN;
		
		music.setVolume(0.5f);
		music.play();
	}
	
	public void shot() {
		
		if (ship.getLife() > 0 && ship.getMissiles() > 0) {
			missiles.genNew();
			ship.deltaMissiles(-1);
		}
	}
	
	public void reboot() {
		
		ship.reboot();
		shield.reboot();
		asteroids.reboot();
		missiles.reboot();
		shipExplosion.reboot();
		
		addActor(ship);
		addActor(shield);

		score = 0;
		
		music.stop();
		
		run();
	}
	
	public void explosion(Entity e, Entity a) {
		
		Explosion ex = explosions.genNew();
		Vector2 pos = GeomUtil.midPoint(e.getCenter(), e.getCenter());
		
		ex.setPosition(pos.x, pos.y);
		ex.setRotation(a.getRotation());
	}
	
	public void deltaScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return score;
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
		return status.state;
	}
}
