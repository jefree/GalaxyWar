package dev.jet.android.galaxywar.world.actors;

public class ShipShield extends Shield {

	public final float TIME_BONUS_LIMIT = 5.0f;
	public final float MAX_SCORE_BONUS = 2.0f;
	public final float DELTA_BONUS = 0.1f;
	
	public final int SHIELD_SCORE = 5;
	
	public float scoreBonus = 1.0f;
	public float timeBonus;
	
	@Override
	public void receiveDamage(float damage) {
		super.receiveDamage(damage);
		
		scoreBonus = 1.0f;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		timeBonus += delta;
		
		if (timeBonus >= TIME_BONUS_LIMIT) {
			if (scoreBonus < MAX_SCORE_BONUS){
				scoreBonus += DELTA_BONUS;
			}
			
			timeBonus = 0;
			
			world.deltaScore((int) (SHIELD_SCORE*scoreBonus));
		}
	}
	
	@Override
	public void reboot() {
		super.reboot();
		
		scoreBonus = 1.0f;
	}
	
}
