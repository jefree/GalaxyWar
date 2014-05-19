package dev.jet.android.galaxywar.world.single;

import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.actors.Shield;
import dev.jet.android.galaxywar.world.single.state.ShipShieldState;

public class ShipShield extends Shield {
	
	private ScoreBonus bonus;
	protected WorldSingle world;
	
	@Override
	public void create(BaseWorld world) {
		super.create(world);
		
		this.world = (WorldSingle) world;
	}
	
	public void setState(EntityState state) {
		
		ShipShieldState data = (ShipShieldState) state;
		
		setRegeneration(data.regeneration);
		bonus = data.bonus;
	}
	
	@Override
	public void receiveDamage(float damage) {
		super.receiveDamage(damage);
		
		bonus.reset();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		bonus.apply(delta);
		bonus.increase(delta);
	}
	
	@Override
	public void reset() {
		super.reset();
		
		bonus.reset();
	}
	
}
