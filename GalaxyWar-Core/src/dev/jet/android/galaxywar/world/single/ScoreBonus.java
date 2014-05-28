package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.single.state.ScoreBonusState;

public class ScoreBonus {
	
	int base;
	int score;
	int maxScore;
	int delta;
	
	float iCounter;
	float iTime;
	
	float aCounter;
	float aTime;
	
	SingleWorld world;
	
	public ScoreBonus(SingleWorld world) {
		this(world, 0, 0, 0);
	}
	
	public ScoreBonus(SingleWorld world, int base, int delta, int max) {
		this(world, base, delta, max, 0, 0);
	}
	
	public ScoreBonus (SingleWorld world, int base, int delta, int max, float iSecs, float aSecs) {
		
		this.world = world;
		
		this.base = base;
		this.delta = delta;
		this.maxScore = max;
		this.iTime = iSecs;
		this.aTime = aSecs;
	}
	
	public void increase() {
		
		if (score < maxScore) {
			score += delta;
			
			if (score > maxScore) {
				score = maxScore;
			}
		}
	}
	
	public void increase(float delta) {
		
		if(iTime <= 0) {
			increase();
		}
		
		iCounter += delta;
		
		if (iCounter >= iTime){
			
			increase();
			iCounter = 0;
		}
	}
	
	public void apply() {
		world.addScore(score);
	}
	
	public void apply(float delta) {
		
		if(aTime <= 0) {
			apply();
		}
		
		aCounter += delta;
		
		if (aCounter >= iTime){
			
			apply();
			aCounter = 0;
		}
	}
	
	public void setState(EntityState state) {
		ScoreBonusState data = (ScoreBonusState) state;
		
		this.base = data.base;
		this.delta = data.delta;
		this.maxScore = data.maxScore;
		this.iTime = data.iTime;
		this.aTime = data.aTime;
		
		aCounter = iCounter = 0;
	}
	
	public void reset() {
		iCounter = 0;
		score = base;
	}
	
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public void setTimeIncrease(float secs) {
		this.iTime = secs;
	}
	
}
