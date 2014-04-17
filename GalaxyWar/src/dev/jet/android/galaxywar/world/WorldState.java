package dev.jet.android.galaxywar.world;

import dev.jet.android.galaxywar.world.state.AsteroidState;
import dev.jet.android.galaxywar.world.state.ShipShieldState;

public final class WorldState {
	
	public static int RUN = 0;
	public static int PAUSE = 1;
	public static int STOP = 2;
	public static int END = 3;
	
	public int state;
	
	public AsteroidState astInitial;
	public AsteroidState astMedium;
	public AsteroidState astHard;
	
	public ShipShieldState shieldInitial;
	public ShipShieldState shieldHard;
	
	public WorldState(World _world) {
		
		astInitial = new AsteroidState();
		astMedium = new AsteroidState();
		astHard = new AsteroidState();
		
		shieldInitial = new ShipShieldState();
		shieldHard = new ShipShieldState();
		
		astInitial.setDamage(20);
		astInitial.setGenTime(1.2f);
		
		astMedium.setDamage(30);
		astMedium.setGenTime(0.8f);
		
		astHard.setDamage(45);
		astHard.setGenTime(0.6f);
		
		ScoreBonification sBInitial = new ScoreBonification(_world);
		ScoreBonification sBHard = new ScoreBonification(_world);
		
		sBInitial.setScore(5);
		sBInitial.setDeltaBonus(0.1f);
		sBInitial.setMaxScoreBonus(2.0f);
		sBInitial.setTimeBonusLimit(5);
		sBInitial.setTimeIncreaseLimit(5);
		
		sBHard.setScore(15);
		sBHard.setDeltaBonus(0.1f);
		sBHard.setMaxScoreBonus(2.0f);
		sBHard.setTimeBonusLimit(5);
		sBHard.setTimeIncreaseLimit(5);
		
		shieldInitial.setRegeneration(5);
		shieldInitial.setBonus(sBInitial);
		
		shieldHard.setRegeneration(3);
		shieldHard.setBonus(sBHard);
				
	}
	
}
