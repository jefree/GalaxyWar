package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.GroupController;
import dev.jet.android.galaxywar.world.actors.Entity;
import dev.jet.android.galaxywar.world.single.TargetGenerator.Target;

public class TargetGenerator extends GroupController<Target> {
	
	TextureRegion image;
	
	public TargetGenerator(BaseWorld world, Media media) {
		super(world);
		
		image = media.getTextureRegion("target");
	}

	public void generate(int n) {
		
		for(int i=1; i < n+1; i++) {
			Target e = create();
			e.setPosition(600*i, 600*i);
		}
	}
	
	public boolean isComplete() {
		
		boolean complete = true;
		
		for (Target t : entities) {
			if (t.enable) {
				complete = false;
				break;
			}
		}
		
		return complete;
	}
	
	@Override
	protected Target createNew() {
		
		Target t = new Target();
		t.create(world, image);
		
		return t;
	}

	@Override
	protected void init(Target e) {
	}

	@Override
	public void setState(EntityState state) {
	}
	
	class Target extends Entity {
		
		@Override
		public void act(float delta) {
			super.act(delta);
			
			if (collide(world.getShip())) {
				destroy();
			}
		}
		
	}
}
