package dev.jet.android.galaxywar.world.state;

public class AsteroidState {
		
	float damage;
	float genTime;
	
	public void setDamage(float damage) {
		this.damage = damage;
	}

	public void setGenTime(float genTime) {
		this.genTime = genTime;
	}

	public float getDamage() {
		return damage;
	}

	public float getGenTime() {
		return genTime;
	}
}
