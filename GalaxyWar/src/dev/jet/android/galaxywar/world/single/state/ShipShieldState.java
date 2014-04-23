package dev.jet.android.galaxywar.world.single.state;

import dev.jet.android.galaxywar.world.single.ScoreBonification;


public class ShipShieldState {
	
	float regeneration;
	ScoreBonification bonus;
	
	public void setBonus(ScoreBonification _bonus) {
		bonus = _bonus;
	}
	
	public ScoreBonification getBonus() {
		return bonus;
	}
	
	public void setRegeneration(float regeneration) {
		this.regeneration = regeneration;
	}
	
	public float getRegeneration() {
		return regeneration;
	}

	
	
}
