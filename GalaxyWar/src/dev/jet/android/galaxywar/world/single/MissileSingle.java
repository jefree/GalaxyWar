package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.actors.Missile;

public class MissileSingle extends Missile {
	
	ScoreBonus bonus;
	WorldSingle world;
	
	@Override
	public void create(BaseWorld world) {
		super.create(world);
		this.world = (WorldSingle) world;
	}
	
	public void setBonus(ScoreBonus bonus) {
		this.bonus = bonus;
	}

	public void onLifeZero(float delta) {
		bonus.reset();
	}
	
	@Override
	public void onAstCollision(float deltaTime) {
		bonus.increase();
		bonus.apply();
	}
}
