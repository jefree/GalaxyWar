package dev.jet.android.galaxywar.world.single.state;

import dev.jet.android.galaxywar.world.EntityState;

public class AsteroidSingleGroupState implements EntityState {
		
	public final float damage;
	public final float genTime;
	
	public final float smallLife;
	public final float bigLife;
	
	public AsteroidSingleGroupState(float damage, float time, int smallLife, int bigLife) {
		this.damage = damage;
		this.genTime = time;
		
		this.smallLife = smallLife;
		this.bigLife = bigLife;
	}
}
