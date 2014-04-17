package dev.jet.android.galaxywar.world.actors;

import dev.jet.android.galaxywar.world.state.ShipShieldState;

public class ShipShield extends Shield {
	
	private ShipShieldState state;
	
	public ShipShield() {
		super();
	}
	
	public void setState(ShipShieldState _state) {
		
		state = _state;
		setRegeneration(state.getRegeneration());
	}
	
	@Override
	public void receiveDamage(float damage) {
		super.receiveDamage(damage);
		
		state.getBonus().reboot();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		state.getBonus().increaseBonus(delta);
		state.getBonus().apply(delta);
	}
	
	@Override
	public void reboot() {
		super.reboot();
		
		state.getBonus().reboot();
	}
	
}
