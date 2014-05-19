package dev.jet.android.galaxywar.world.single.state;

import dev.jet.android.galaxywar.world.EntityState;

public class ScoreBonusState implements EntityState {
	
	public final int base;
	public final int delta;
	public final int maxScore;
	public final float iTime;
	public final float aTime;
	
	public ScoreBonusState(int base, int delta, int max, float iTime, float aTime) {
		this.base = base;
		this.delta = delta;
		this.maxScore = max;
		this.iTime = iTime;
		this.aTime = aTime;
	}
}
