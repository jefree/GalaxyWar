package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.utils.Timer;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.MathUtil;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.single.state.AsteroidSingleGroupState;
import dev.jet.android.galaxywar.world.single.state.ScoreBonusState;
import dev.jet.android.galaxywar.world.single.state.ShipShieldState;

public class WorldSingle extends BaseWorld {
	
	protected int score;
	
	public WorldSingle(Media media) {
		super(media);
		
		status = WorldState.STOP;
		
		addStates();
		
		shield.setState(getState("shieldI"));
		asteroids.setState(getState("astI"));
		missiles.setState(getState("mBInitial"));
	}
	
	@Override
	public void act(float delta) {
		
		if (status == WorldState.RUN && ship.getLife() < 0) {
			
			status = WorldState.STOP;
			
			addActor(shipExplosion);
			
			music.setVolume(0.1f);
			
			shipExplosion.playSound();
			
			ship.remove();
			shield.remove();
		
		} else if(status == WorldState.STOP) { 
			
			shipExplosion.setPosition(ship.getX(), ship.getY());
			
			if(shipExplosion.isFinished()) {
				status = WorldState.END;
			}
		} 
		
		if (status != WorldState.PAUSE) {
			
			super.act(delta);
			
			offsetX = getWidth()/2 - ship.getX();
			offsetY = getHeight()/2 - ship.getY();
		} 
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return score;
	}
	
	
	@Override
	public void setActions() {
		
		Timer.Task medium = new Timer.Task() {
			@Override
			public void run() {
				
				asteroids.setState(getState("astM"));
				missiles.setState(getState("mBMedium"));
			}
		};
		
		Timer.Task hard = new Timer.Task() {
			@Override
			public void run() {
				
				asteroids.setState(getState("astH"));
				shield.setState(getState("shieldH"));
				missiles.setState(getState("mBHard"));
			}
		};
		
		Timer.schedule(medium, MathUtil.toSeconds(0, 15));
		Timer.schedule(hard, MathUtil.toSeconds(0, 30));
	} 
	
	public void addStates() {
		
		AsteroidSingleGroupState astInitial = new AsteroidSingleGroupState(20, 1.2f);
		AsteroidSingleGroupState astMedium = new AsteroidSingleGroupState(30, 0.8f);
		AsteroidSingleGroupState astHard = new AsteroidSingleGroupState(45, 0.6f);
		
		ScoreBonusState mBInitial = new ScoreBonusState(10, 10, 50, 0, 0);
		ScoreBonusState mBMedium = new ScoreBonusState(15, 15, 75, 0, 0);
		ScoreBonusState mBHard = new ScoreBonusState(20, 20, 100, 0, 0);
		
		ScoreBonus sBInitial = new ScoreBonus(this, 5, 5, 30, 5, 5);
		ScoreBonus sBHard = new ScoreBonus(this, 5, 5, 60, 5, 5);
		
		ShipShieldState shieldInitial = new ShipShieldState(5, sBInitial);
		ShipShieldState shieldHard = new ShipShieldState(3, sBHard);
				
		addState("astI", astInitial);
		addState("astM", astMedium);
		addState("astH", astHard);
		
		addState("shieldI", shieldInitial);
		addState("shieldH", shieldHard);
		
		addState("mBInitial", mBInitial);
		addState("mBMedium", mBMedium);
		addState("mBHard", mBHard);
	}

}
