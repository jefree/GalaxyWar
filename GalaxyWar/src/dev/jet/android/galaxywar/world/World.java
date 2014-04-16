package dev.jet.android.galaxywar.world;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.media.TapestryPicture;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.utils.MathUtil;
import dev.jet.android.galaxywar.world.actors.Asteroid;
import dev.jet.android.galaxywar.world.actors.Entity;
import dev.jet.android.galaxywar.world.actors.Explosion;
import dev.jet.android.galaxywar.world.actors.Missile;
import dev.jet.android.galaxywar.world.actors.Shield;
import dev.jet.android.galaxywar.world.actors.Ship;
import dev.jet.android.galaxywar.world.actors.ShipShield;
import dev.jet.android.galaxywar.world.state.AsteroidState;

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
	private int score;
	
	private int gameTime;
	
	Entity back;
	TapestryPicture background;
	Music music;
	
	public World (Media media) {
		
		setWidth(media.getScreenWidth());
		setHeight(media.getScreenHeight());
		
		state = STOP;
		
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
		
		if (state == RUN && ship.getLife() < 0) {
			
			state = STOP;
			
			addActor(shipExplosion);
			
			music.setVolume(0.1f);
			
			shipExplosion.playSound();
			
			ship.remove();
			shield.remove();
		
		} else if(state == STOP) { 
			
			shipExplosion.setPosition(ship.getX(), ship.getY());
			
			if(shipExplosion.isFinished()) {
				state = END;
			}
		} 
		
		if (state != PAUSE) {
			
			gameTime += delta;
			
			if (gameTime > MathUtil.toSeconds(1, 0)) {
				asteroids.setState(AsteroidState.hard);
			
			} else if (gameTime > MathUtil.toSeconds(0, 30)){
				asteroids.setState(AsteroidState.medium);
			}
			
			super.act(delta);
			
			offsetX = getWidth()/2 - ship.getX();
			offsetY = getHeight()/2 - ship.getY();
		} 
	}
	
	public void pause() {
		state = PAUSE;
		
		music.pause();
	}
	
	public void run() {
		state = RUN;
		
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
				asteroids.setState(AsteroidState.medium);
			}
		};
		
		Timer.Task hard = new Timer.Task() {
			@Override
			public void run() {
				asteroids.setState(AsteroidState.hard);
			}
		};
		
		Timer.schedule(medium, MathUtil.toSeconds(0, 45));
		Timer.schedule(hard, MathUtil.toSeconds(1, 30));
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
		return state;
	}
}
