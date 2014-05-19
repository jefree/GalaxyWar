package dev.jet.android.galaxywar.world.single.state;

import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.single.ScoreBonus;

public class MissileSingleState implements EntityState {
	
	public final float life;
	public final int speed;
	public final ScoreBonus bonus;
	
	public MissileSingleState(float life, int speed, ScoreBonus bonus) {
		this.life = life;
		this.speed = speed;
		this.bonus = bonus;
	}
	
}
