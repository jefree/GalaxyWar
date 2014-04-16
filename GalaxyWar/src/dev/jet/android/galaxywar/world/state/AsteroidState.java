package dev.jet.android.galaxywar.world.state;

public class AsteroidState {
	
	public static AsteroidState initial;
	public static AsteroidState medium;
	public static AsteroidState hard;
	
	static {
		
		initial = new AsteroidState(20f, 1.0f);
		medium = new AsteroidState(30f, 0.8f);
		hard = new AsteroidState(45f, 0.8f);
	}
	
	float damage;
	float genTime;
	
	public AsteroidState(float _damage, float _genTime) {
		damage = _damage;
		genTime = _genTime;
	}
	
	public float getDamage() {
		return damage;
	}

	public float getGenTime() {
		return genTime;
	}
}
