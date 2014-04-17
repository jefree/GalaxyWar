package dev.jet.android.galaxywar.world;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.utils.MathUtil;
import dev.jet.android.galaxywar.world.actors.Asteroid;
import dev.jet.android.galaxywar.world.actors.Entity;
import dev.jet.android.galaxywar.world.actors.Explosion;
import dev.jet.android.galaxywar.world.actors.Missile;
import dev.jet.android.galaxywar.world.actors.Shield;
import dev.jet.android.galaxywar.world.actors.Ship;
import dev.jet.android.galaxywar.world.actors.ShipShield;


public class World extends Group {
	
	private Ship ship;
	private ShipShield shield;
	private Explosion shipExplosion;
	
	private MissileController missiles;
	private AsteroidsController asteroids;
	private AstExplosionController explosions;
	
	private WorldState status;
	
	private Entity back;
	private Music music;
	
	private float offsetX;
	private float offsetY;
	
	private int score;
	
	
	public World (Media media) {
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		offsetX = getWidth()/2;
		offsetY = getHeight()/2;
		
		status = new WorldState(this);
		status.state = WorldState.STOP;
		
		ship = new Ship();
		ship.create(this, media.getPicture("ship"), null);
		
		shield = new ShipShield();
		shield.setState(status.shieldInitial);
		shield.create(this, media.getPicture("shield"), null);
		shield.setDefended(ship);
		
		shipExplosion =  new Explosion();
		shipExplosion.create(this, media.getPicture("explosion/ship/anim"), media.getSound("sounds/explosionShip"));
		shipExplosion.setDuration(3);
		
		asteroids = new AsteroidsController(this, media);
		asteroids.setState(status.astInitial);
		
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
		
		if (status.state == WorldState.RUN && ship.getLife() < 0) {
			
			status.state = WorldState.STOP;
			
			addActor(shipExplosion);
			
			music.setVolume(0.1f);
			
			shipExplosion.playSound();
			
			ship.remove();
			shield.remove();
		
		} else if(status.state == WorldState.STOP) { 
			
			shipExplosion.setPosition(ship.getX(), ship.getY());
			
			if(shipExplosion.isFinished()) {
				status.state = WorldState.END;
			}
		} 
		
		if (status.state != WorldState.PAUSE) {
			
			super.act(delta);
			
			offsetX = getWidth()/2 - ship.getX();
			offsetY = getHeight()/2 - ship.getY();
		} 
	}
	
	public void pause() {
		status.state = WorldState.PAUSE;
		
		music.pause();
	}
	
	public void run() {
		status.state = WorldState.RUN;
		
		music.setVolume(0.5f);
		music.play();
		
	}
	
	public void reboot() {
		
		ship.reboot();
		shield.reboot();
		asteroids.reboot();
		missiles.reboot();
		shipExplosion.reboot();
		
		addActor(ship);
		addActor(shield);
		
		Missile.scoreBonus = 1.0f;
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
	
	public void shot() {
		
		if (ship.getLife() > 0 && ship.getMissiles() > 0) {
			missiles.genNew();
			ship.deltaMissiles(-1);
		}
	}
	
	public void setTimers () {
		
		Timer.Task medium = new Timer.Task() {
			@Override
			public void run() {
				asteroids.setState(status.astMedium);
			}
		};
		
		Timer.Task hard = new Timer.Task() {
			@Override
			public void run() {
				asteroids.setState(status.astHard);
				shield.setState(status.shieldHard);
			}
		};
		
		Timer.schedule(medium, MathUtil.toSeconds(0, 15));
		Timer.schedule(hard, MathUtil.toSeconds(0, 30));
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
