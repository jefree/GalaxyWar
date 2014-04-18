package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.world.actors.Missile;

public class MissileSingle extends Missile {
	
	public static ScoreBonification bonus;
	
	public static void setBonus(ScoreBonification _bonus) {
		bonus = _bonus;
	}
	
	public void onLifeZero(float delta) {
		bonus.reboot();
	}
	
	@Override
	public void onAstCollision(float deltaTime) {
		
		System.out.println("missile");
		
		bonus.increase(deltaTime);
		bonus.apply(deltaTime);
	}
}
