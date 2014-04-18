package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.world.World;

public class ScoreBonification {
	
	float timeBonusLimit;
	float timeIncreaseLimit;
	
	float maxScoreBonus;
	float deltaBonus;
	
	float timeBonus;
	float timeIncrease;
	
	float scoreBonus;
	float score;
	
	World world;
	
	public ScoreBonification(World _world, float _score, float delta, float max, float tBonus, float tIncrease ) {
		world = _world;
		
		score = _score;
		deltaBonus = delta;
		maxScoreBonus = max;
		timeBonusLimit = tBonus;
		timeIncreaseLimit = tIncrease;
	}
	
	public void apply(float deltaTime) {
		
		timeBonus += deltaTime;
		
		if (timeBonus >= timeBonusLimit) {
			
			System.out.println("gano: "+ score*scoreBonus);
			
			world.deltaScore( (int) (score*scoreBonus) );
			timeBonus = 0;
			
		}
	}
	
	public void increase(float deltaTime) {
		
		timeIncrease += deltaTime;
		
		if (timeIncrease >= timeIncreaseLimit) {
			
			if (scoreBonus < maxScoreBonus) {
				scoreBonus += deltaBonus;
			}
			
			timeIncrease = 0;
		}
	}
	
	public void reboot() {
		scoreBonus = 1.0f;
		timeIncrease = 0.0f;
	}
}
