package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.GroupController;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.single.state.MissileSingleState;

public class MissileSingleGroup extends GroupController<MissileSingle> {
	
	TextureRegion image;
	Sound sound;
	
	MissileSingleState state;
	
	public MissileSingleGroup(BaseWorld world, Media media){
		
		super(world);
		
		image = media.getTextureRegion("missile");
		sound = media.getSound("sounds/shot");
	}
	
	@Override
	protected void init(MissileSingle missile) {
		
		Vector2 delta = GeomUtil.getVector2(world.getShip().getHeight()/2 + missile.getHeight()/2, 
					world.getShip().getDirAngle());
		
		missile.setPosition(delta.x, delta.y);
		missile.setRotation(world.getShip().getDirAngle());
		
		missile.setState(state);
		
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
		this.state = (MissileSingleState) state;
	}
}
