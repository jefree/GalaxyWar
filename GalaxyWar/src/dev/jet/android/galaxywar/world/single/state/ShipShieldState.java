package dev.jet.android.galaxywar.world.single.state;

import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.single.ScoreBonus;

public class ShipShieldState implements EntityState {
	
	public final float regeneration;
	public final ScoreBonus bonus;
	
	public ShipShieldState(float regeneration, ScoreBonus bonus) {
		this.regeneration = regeneration;
		this.bonus = bonus;
	}
}
