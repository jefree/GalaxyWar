package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.GroupController;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.actors.Ship;

public class MissileSingleGroup extends GroupController<MissileSingle> {

	private static final float MISSILE_LIFE = 4; 
	private static final int MISSILE_SPEED = 200; 
	
	TextureRegion image;
	Sound sound;
	
	ScoreBonus bonus;
	
	public MissileSingleGroup(BaseWorld world, Media media){
		
		super(world);
		
		image = media.getTextureRegion("missile");
		sound = media.getSound("sounds/shot");
		
		bonus = new ScoreBonus( (WorldSingle) world );
	}
	
	@Override
	protected void init(MissileSingle missile) {
		
		Ship ship = getWorld().getShip();
		float delta[] = GeomUtil.getSides(ship.getHeight()/2 + missile.getHeight()/2, ship.getRotation());
		
		missile.setRotation(ship.getRotation());
		missile.setPosition(ship.getX() + delta[0],
							ship.getY() + delta[1]);
		
		missile.setSpeed(MISSILE_SPEED + world.getShip().getSpeed());
		missile.setLife(MISSILE_LIFE);
		
		missile.setBonus(bonus);
		
		missile.playSound(0.5f);
	}

	@Override
	protected MissileSingle createNew() {
		
		MissileSingle m = new MissileSingle();
		m.create(world, image, sound);
		
		return m;
		
	}

	@Override
	public void setState(EntityState state) {
		bonus.setState(state);
	}
}
