package dev.jet.android.galaxywar.world.single.state;

import dev.jet.android.galaxywar.world.EntityState;

public class AsteroidSingleGroupState implements EntityState {
		
	public final float damage;
	public final float genTime;
	
	public AsteroidSingleGroupState(float damage, float time) {
		this.damage = damage;
		this.genTime = time;
	}
}
