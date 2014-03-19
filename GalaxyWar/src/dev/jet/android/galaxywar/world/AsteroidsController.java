package dev.jet.android.galaxywar.world;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.UtilityMath;
import dev.jet.android.galaxywar.world.actors.Asteroid;

public class AsteroidsController extends GroupController<Asteroid> {
	
	private static final int MAX_ASTEROIDS_NUMBER = 25;
	
	private static final int MAX_ASTEROIDS_ROTATTION_SPEED = 2;
	private static final int MIN_ASTEROIDS_ROTATTION_SPEED = 1;
	
	private static final float ASTEROID_TIME_GENERATION = 1f;
	
	private static final float MAX_ASTEROID_SPEED = 100;
	private static final float MIN_ASTEROID_SPEED = 70;
	
	private int MIN_GEN_RADIUS = 0;
	private int MAX_GEN_RADIUS = 0;	
	
	private float genTime;
	private float deltaTime;
	
	public AsteroidsController(World world, Media media) {
		
		super(Asteroid.class, world, media.getPicture("asteroid"), MAX_ASTEROIDS_NUMBER);
		
		genTime = ASTEROID_TIME_GENERATION;
		deltaTime = 0;
		
		MIN_GEN_RADIUS = (int)(world.getWidth()/2 + getWidth()/2);
		MAX_GEN_RADIUS = (int)(MIN_GEN_RADIUS*1.1); 
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
		
		ast.setSpeedRotation(UtilityMath.getRandom(MIN_ASTEROIDS_ROTATTION_SPEED, MAX_ASTEROIDS_ROTATTION_SPEED));
		
		shipX = (int)world.getShip().getX();
		shipY = (int)world.getShip().getY();
		
		astX = (int)UtilityMath.getRandom(MIN_GEN_RADIUS, MAX_GEN_RADIUS, true) + shipX;
		astY = (int)UtilityMath.getRandom(MIN_GEN_RADIUS, MAX_GEN_RADIUS, true) + shipY;
		
		ast.setPosition(astX, astY);
		ast.setDirAngle((float) UtilityMath.getAngle(astX, astY, shipX, shipY));
		ast.setSpeed((int)UtilityMath.getRandom(MIN_ASTEROID_SPEED, MAX_ASTEROID_SPEED));
	}
}
