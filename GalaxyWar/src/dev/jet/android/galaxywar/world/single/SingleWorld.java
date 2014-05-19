package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.single.state.AsteroidSingleGroupState;
import dev.jet.android.galaxywar.world.single.state.ScoreBonusState;
import dev.jet.android.galaxywar.world.single.state.ShipShieldState;

public class SingleWorld extends BaseWorld {
	
	protected int score;
	
	public SingleWorld(Media media) {
		super(media);
		
		status = WorldState.STOP;
		
		addStates();
		
		setFocusActor(ship);
		
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
		} 
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return score;
	}
	
	
	@Override
	public void addActions() {
		
		DelayAction mediumLevel = new DelayAction(15);
		DelayAction hardLevel = new DelayAction(30);
		
		mediumLevel.setAction(new Action() {
			@Override
			public boolean act(float delta) {
				
				System.out.println("Enter Medium Lv");
				
				asteroids.setState(getState("astM"));
				missiles.setState(getState("mBMedium"));
				
				return true;
			}
		});
		
		hardLevel.setAction(new Action() {
			@Override
			public boolean act(float delta) {
				
				System.out.println("Enter Hard Lv");
				
				asteroids.setState(getState("astH"));
				shield.setState(getState("shieldH"));
				missiles.setState(getState("mBHard"));
				
				return true;
			}
		});
		
		addAction(mediumLevel);
		addAction(hardLevel);
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
