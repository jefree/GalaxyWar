package dev.jet.android.galaxywar.world;

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
	
	public ScoreBonification(World _world) {
		world = _world;
	}
	
	public void apply(float deltaTime) {
		
		timeBonus += deltaTime;
		
		if (timeBonus >= timeBonusLimit) {
			world.deltaScore( (int) (score*scoreBonus) );
			timeBonus = 0;
			
		}
	}
	
	public void increaseBonus(float deltaTime) {
		
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
		timeBonus = 0.0f;
	}
	
	public void setTimeBonusLimit(float timeBonusLimit) {
		this.timeBonusLimit = timeBonusLimit;
	}
	
	public void setTimeIncreaseLimit(float timeIncreaseLimit) {
		this.timeIncreaseLimit = timeIncreaseLimit;
	}

	public void setMaxScoreBonus(float maxScoreBonus) {
		this.maxScoreBonus = maxScoreBonus;
	}
	
	public void setDeltaBonus(float deltaBonus) {
		this.deltaBonus = deltaBonus;
	}
	
	public void setScore(float score) {
		this.score = score;
	}
	
}
