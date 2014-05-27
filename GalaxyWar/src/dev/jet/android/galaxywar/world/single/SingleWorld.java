package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.single.state.AsteroidSingleGroupState;
import dev.jet.android.galaxywar.world.single.state.MissileSingleState;
import dev.jet.android.galaxywar.world.single.state.ShipShieldState;

public class SingleWorld extends BaseWorld {
	
	protected int score;
	
	protected TargetGenerator targets;
	
	public SingleWorld(Media media) {
		super(media);
		
		status = WorldState.STOP;
		targets = new TargetGenerator(this, media);
		
		addStates();
		
		setFocusActor(ship);
		
		addActorBefore(asteroids, targets);
	}
	
	@Override
	public void run() {
		super.run();
		
		addAction(new Action() {
			
			@Override
			public boolean act(float arg0) {
				
				if(ship.life <= 0) {

					createShipExplosion();
					return true;
				}
				
				if (targets.isComplete()) {
					
					createShipExplosion();
					return true;
				}
				
				return false;
			}
		});
	}
	
	@Override
	public void act(float delta) {
		
		if (status == WorldState.PAUSE){
			return;
		}
		
		super.act(delta);
	}
	
	public void reset() {

		score = 0;
		
		shield.setState(getState("shieldI"));
		asteroids.setState(getState("astI"));
		missiles.setState(getState("mInitial"));
		
		ship.reset();
		shield.reset();
		asteroids.reset();
		missiles.reset();
		shipExplosion.reset();
		targets.reset();
		
		clearActions();
		
		targets.generate(2);
		createLevelUpActions();
		
		addActor(ship);
		addActor(shield);
		
		music.stop();
	}
	
	private void createShipExplosion() {
		
		status = WorldState.STOP;

		shipExplosion.setPosition(ship.getX(), ship.getY());
		addActor(shipExplosion);
		
		ship.enable = false;
		ship.remove();
		shield.remove();
		
		music.setVolume(0.1f);
		shipExplosion.playSound();

		addAction(new Action() {
			
			@Override
			public boolean act(float arg0) {
				if(shipExplosion.isFinished()) {
					
					status = WorldState.END;
					return true;
				}
				
				return false;
			}
		});
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void createLevelUpActions() {
		
		DelayAction mediumLevel = new DelayAction(15);
		DelayAction hardLevel = new DelayAction(30);
		
		mediumLevel.setAction(new Action() {
			@Override
			public boolean act(float delta) {
				
				asteroids.setState(getState("astM"));
				missiles.setState(getState("mMedium"));
				
				return true;
			}
		});
		
		hardLevel.setAction(new Action() {
			@Override
			public boolean act(float delta) {
				
				asteroids.setState(getState("astH"));
				shield.setState(getState("shieldH"));
				missiles.setState(getState("mHard"));
				
				return true;
			}
		});
		
		addAction(mediumLevel);
		addAction(hardLevel);
	} 
	
	public void addStates() {
		
		AsteroidSingleGroupState astInitial = new AsteroidSingleGroupState(20, 1.2f, 1, 2);
		AsteroidSingleGroupState astMedium = new AsteroidSingleGroupState(30, 0.8f, 1, 2);
		AsteroidSingleGroupState astHard = new AsteroidSingleGroupState(45, 0.6f, 1, 2);
		
		ScoreBonus mBInitial = new ScoreBonus(this, 10, 10, 50, 0, 0);
		ScoreBonus mBMedium = new ScoreBonus(this, 15, 15, 75, 0, 0);
		ScoreBonus mBHard = new ScoreBonus(this, 20, 20, 100, 0, 0);
		
		ScoreBonus sBInitial = new ScoreBonus(this, 5, 5, 30, 5, 5);
		ScoreBonus sBHard = new ScoreBonus(this, 5, 5, 60, 5, 5);
		
		ShipShieldState shieldInitial = new ShipShieldState(5, sBInitial);
		ShipShieldState shieldHard = new ShipShieldState(3, sBHard);
		
		MissileSingleState missileInitial = new MissileSingleState(4, 200, mBInitial);
		MissileSingleState missileMedium = new MissileSingleState(4, 200, mBMedium);
		MissileSingleState missileHard = new MissileSingleState(4, 200, mBHard);
				
		addState("astI", astInitial);
		addState("astM", astMedium);
		addState("astH", astHard);
		
		addState("shieldI", shieldInitial);
		addState("shieldH", shieldHard);
		
		addState("mInitial", missileInitial);
		addState("mMedium", missileMedium);
		addState("mHard", missileHard);
	}

}
