package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.WorldState;
import dev.jet.android.galaxywar.world.single.state.AsteroidSingleState;
import dev.jet.android.galaxywar.world.single.state.ShipShieldState;

public final class WorldStateSingle extends WorldState {
	
	public WorldStateSingle(BaseWorld _world) {
		
		AsteroidSingleState astInitial = new AsteroidSingleState();
		AsteroidSingleState astMedium = new AsteroidSingleState();
		AsteroidSingleState astHard = new AsteroidSingleState();
		
		ShipShieldState shieldInitial = new ShipShieldState();
		ShipShieldState shieldHard = new ShipShieldState();
		
		astInitial.setDamage(20);
		astInitial.setGenTime(1.2f);
		
		astMedium.setDamage(30);
		astMedium.setGenTime(0.8f);
		
		astHard.setDamage(45);
		astHard.setGenTime(0.6f);
		
		ScoreBonification mBInitial = new ScoreBonification(_world, 10, 0.1f, 3, 0 ,0);
		ScoreBonification mBMedium = new ScoreBonification(_world, 15, 0.2f, 3, 0 ,0);
		ScoreBonification mBHard = new ScoreBonification(_world, 20, 0.5f, 3, 0 ,0);
		
		ScoreBonification sBInitial = new ScoreBonification(_world, 5, 0.1f, 2.0f, 5, 5);
		ScoreBonification sBHard = new ScoreBonification(_world, 15, 0.1f, 2.0f, 5, 5);
		
		shieldInitial.setRegeneration(5);
		shieldInitial.setBonus(sBInitial);
		
		shieldHard.setRegeneration(3);
		shieldHard.setBonus(sBHard);
				
		states.add("astI", astInitial, AsteroidSingleState.class);
		states.add("astM", astMedium, AsteroidSingleState.class);
		states.add("astH", astHard, AsteroidSingleState.class);
		
		states.add("shieldI", shieldInitial, ShipShieldState.class);
		states.add("shieldH", shieldHard, ShipShieldState.class);
		
		states.add("mBInitial", mBInitial, ScoreBonification.class);
		states.add("mBMedium", mBMedium, ScoreBonification.class);
		states.add("mBHard", mBHard, ScoreBonification.class);
	}
	
}
