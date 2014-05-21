package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.actors.Missile;
import dev.jet.android.galaxywar.world.single.state.MissileSingleState;

public class MissileSingle extends Missile {
	
	ScoreBonus bonus;
	SingleWorld world;
	
	@Override
	public void create(BaseWorld world) {
		super.create(world);
		this.world = (SingleWorld) world;
	}
	
	public void setBonus(ScoreBonus bonus) {
		this.bonus = bonus;
	}

	public void onDestroyed(float delta) {
		bonus.reset();
	}
	
	@Override
	public void onAstCollision(float deltaTime) {
		bonus.increase();
		bonus.apply();
	}
	
	@Override
	public void setState(EntityState state) {
		super.setState(state);
		
		MissileSingleState data = (MissileSingleState) state;
		
		life = data.life;
		speed = data.speed;
		bonus = data.bonus;
	}
}
