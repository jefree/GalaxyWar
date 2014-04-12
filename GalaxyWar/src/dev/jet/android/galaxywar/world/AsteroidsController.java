package dev.jet.android.galaxywar.world;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.utils.MathUtil;
import dev.jet.android.galaxywar.world.actors.Asteroid;

public class AsteroidsController extends GroupController<Asteroid> {
	
	private static final int MAX_ASTEROIDS_NUMBER = 25;
	
	private static final int MAX_ASTEROIDS_ROTATTION_SPEED = 2;
	private static final int MIN_ASTEROIDS_ROTATTION_SPEED = 1;
	
	private static final float ASTEROID_TIME_GENERATION = 1.0f;
	
	private static final float MAX_ASTEROID_SPEED = 100;
	private static final float MIN_ASTEROID_SPEED = 70;
	
	private int GEN_RADIUS = 0;
	private int DISP_RADIUS = 0;	
	
	
	private float genTime;
	private float deltaTime;
	
	public AsteroidsController(World world, Media media) {
		
		super(Asteroid.class, world, media.getPicture("asteroid"), null, MAX_ASTEROIDS_NUMBER);
		
		genTime = ASTEROID_TIME_GENERATION;
		deltaTime = 0;
		
		GEN_RADIUS = (int)(world.getWidth()/2 + media.getPicture("asteroid").getWidth()/2);
		DISP_RADIUS = (int)(GEN_RADIUS*0.3f); 
	}
	
	@Override
	public boolean shouldGenerate(float delta) {
		
		deltaTime += delta;
		
		if(deltaTime >= genTime) {
			deltaTime = 0;
			return true;
		}
		
		return false;
	}

	@Override
	public void initEntity(Asteroid ast) {
		
		int astX, astY, shipX, shipY;
		
		float[] delta;
		float borderAngle;
		float disp;
		
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
		
		ast.setPosition(astX, astY);
		ast.setDirAngle((float) GeomUtil.getAngle(astX, astY, shipX, shipY));
		ast.setSpeed((int)MathUtil.getRandom(MIN_ASTEROID_SPEED, MAX_ASTEROID_SPEED));
	}
}
