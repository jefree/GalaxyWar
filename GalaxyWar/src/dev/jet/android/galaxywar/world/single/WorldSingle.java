package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.utils.Timer;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.MathUtil;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.single.state.AsteroidSingleState;
import dev.jet.android.galaxywar.world.single.state.ShipShieldState;

public class WorldSingle extends BaseWorld {
	
	public WorldSingle(Media media) {
		super(media);
		
		this.status = new WorldStateSingle(this);
		this.status.state = WorldStateSingle.STOP;
		
		shield.setState(status.getState("shieldI", ShipShieldState.class));
		asteroids.setState(status.getState("astI", AsteroidSingleState.class));
		MissileSingle.setBonus(status.getState("mBInitial", ScoreBonification.class));
		
	}
	
	@Override
	public void act(float delta) {
		
		if (status.state == WorldStateSingle.RUN && ship.getLife() < 0) {
			
			status.state = WorldStateSingle.STOP;
			
			addActor(shipExplosion);
			
			music.setVolume(0.1f);
			
			shipExplosion.playSound();
			
			ship.remove();
			shield.remove();
		
		} else if(status.state == WorldStateSingle.STOP) { 
			
			shipExplosion.setPosition(ship.getX(), ship.getY());
			
			if(shipExplosion.isFinished()) {
				status.state = WorldStateSingle.END;
			}
		} 
		
		if (status.state != WorldStateSingle.PAUSE) {
			
			super.act(delta);
			
			offsetX = getWidth()/2 - ship.getX();
			offsetY = getHeight()/2 - ship.getY();
		} 
	}
	
	public void setTimers () {
		
		Timer.Task medium = new Timer.Task() {
			@Override
			public void run() {
				
				asteroids.setState(status.getState("astM", AsteroidSingleState.class));
				MissileSingle.setBonus(status.getState("mBMedium", ScoreBonification.class));
			}
		};
		
		Timer.Task hard = new Timer.Task() {
			@Override
			public void run() {
				
				asteroids.setState(status.getState("astH", AsteroidSingleState.class));
				shield.setState(status.getState("shieldH", ShipShieldState.class));
				MissileSingle.setBonus(status.getState("mBHard", ScoreBonification.class));
			}
		};
		
		Timer.schedule(medium, MathUtil.toSeconds(0, 15));
		Timer.schedule(hard, MathUtil.toSeconds(0, 30));
	} 

}
