package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.utils.MathUtil;
import dev.jet.android.galaxywar.world.GroupController;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.single.state.AsteroidSingleState;

public class AsteroidsController extends GroupController<AsteroidSingle> {
	
	private static final int MAX_ASTEROIDS_NUMBER = 25;
	
	private static final int MAX_ASTEROIDS_ROTATTION_SPEED = 2;
	private static final int MIN_ASTEROIDS_ROTATTION_SPEED = 1;
	
	private static final float MAX_ASTEROID_SPEED = 100;
	private static final float MIN_ASTEROID_SPEED = 70;
	
	private static final float INIT_GEN_TIME_LIMIT = 3.0f;
	
	private int GEN_RADIUS = 0;
	private int DISP_RADIUS = 0;	
	
	private TextureRegion image;
	
	private float genTime;
	private float deltaTime;
	
	private float initGenTime;
	
	private AsteroidSingleState state;
	
	public AsteroidsController(BaseWorld world, Media media) {
		
		super(AsteroidSingle.class, world, MAX_ASTEROIDS_NUMBER);
		
		deltaTime = 0;
		
		image = media.getTextureRegion("asteroid");
		
		GEN_RADIUS = (int)(world.getWidth()/2 + media.getTextureRegion("asteroid").getRegionWidth()/2);
		DISP_RADIUS = (int)(GEN_RADIUS*0.3f); 
	}
	
	public void setState(AsteroidSingleState _state) {
		state = _state;
		
		genTime = state.getGenTime();
	}
	
	@Override
	public boolean shouldGenerate(float delta) {
		
		if (initGenTime <= INIT_GEN_TIME_LIMIT){
			initGenTime += delta;
			return false;
		} 
		
		deltaTime += delta;
		
		if(deltaTime >= genTime) {
			deltaTime = 0;
			return true;
		}
		
		return false;
	}

	@Override
	public void initEntity(AsteroidSingle ast) {
		
		int astX, astY, shipX, shipY;
		
		float[] delta;
		float borderAngle;
		float disp;
		
		ast.setImage(image);
		ast.setSpeedRotation(MathUtil.getRandom(MIN_ASTEROIDS_ROTATTION_SPEED, MAX_ASTEROIDS_ROTATTION_SPEED));
		
		shipX = (int)world.getShip().getX();
		shipY = (int)world.getShip().getY();
		
		delta = GeomUtil.getSides(GEN_RADIUS, world.getShip().getRotation());
		borderAngle = world.getShip().getRotation() + 90; 
		
		astX = shipX + (int)delta[0];
		astY = shipY + (int)delta[1];
		
		disp = MathUtil.getRandSign() * DISP_RADIUS;
		delta = GeomUtil.getSides(disp, borderAngle);
		
		astX += (int)delta[0];
		astY += (int)delta[1];
		
		ast.setDamage( (int)state.getDamage());
		
		ast.setPosition(astX, astY);
		ast.setDirAngle((float) GeomUtil.getAngle(astX, astY, shipX, shipY));
		ast.setSpeed((int)MathUtil.getRandom(MIN_ASTEROID_SPEED, MAX_ASTEROID_SPEED));
	}
	
	@Override
	public void reboot() {
		super.reboot();
		
		initGenTime = 0.0f;
	}
}
