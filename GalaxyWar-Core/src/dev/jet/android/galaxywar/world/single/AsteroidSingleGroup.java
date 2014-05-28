package dev.jet.android.galaxywar.world.single;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import dev.jet.android.galaxywar.media.Media;
import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.utils.MathUtil;
import dev.jet.android.galaxywar.world.EntityState;
import dev.jet.android.galaxywar.world.GroupController;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.actors.Asteroid.AsteroidType;
import dev.jet.android.galaxywar.world.single.state.AsteroidSingleGroupState;

public class AsteroidSingleGroup extends GroupController<AsteroidSingle> {
	
	private static final int MAX_ASTEROIDS_ROTATTION_SPEED = 2;
	private static final int MIN_ASTEROIDS_ROTATTION_SPEED = 1;
	
	private static final float MAX_ASTEROID_SPEED = 100;
	private static final float MIN_ASTEROID_SPEED = 70;
	
	private static final float INIT_GEN_TIME_LIMIT = 3.0f;
	
	private TextureRegion smallAstImage;
	private TextureRegion bigAstImage;
	
	private float genTime;
	private float deltaTime;
	
	private float initGenTime;
	
	private AsteroidSingleGroupState state;
	
	public AsteroidSingleGroup(BaseWorld world, Media media) {
		super(world);
		
		deltaTime = 0;
		
		smallAstImage = media.getTextureRegion("smallAsteroid");
		bigAstImage = media.getTextureRegion("bigAsteroid");
		
		//Entity.debug(true);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (initGenTime <= INIT_GEN_TIME_LIMIT){
			initGenTime += delta;
			return;
		}
		
		deltaTime += delta;
		
		if(deltaTime >= genTime) {
			deltaTime = 0;
			create();
		}
	}

	@Override
	protected void init(AsteroidSingle ast) {
		
		float astX, astY, shipX, shipY;
		float genRadius, dispRadius;
		
		Vector2 delta;
		float regionBorderAngle;
		float disp;
		
		TextureRegion image = null;
		AsteroidType type = null;
		float damage = 0;
		float life = 0; 
		float speed = 0;
		
		/* determinate the asteroid type */
		
		if (Math.random() < 0.1){
			
			type = AsteroidType.BIG;
			image = bigAstImage;
			damage = state.damage * 2;
			speed = MAX_ASTEROID_SPEED * 1.5f;
			life = 2;
			
		
		} else {
			
			type = AsteroidType.SMALL;
			image = smallAstImage;
			damage = state.damage;
			speed = MathUtil.getRandom(MIN_ASTEROID_SPEED, MAX_ASTEROID_SPEED);
			life = 1;
		}
		
		ast.setImage(image);
		ast.damage = damage;
		ast.life = life;
		ast.type = type;
		
		genRadius = world.getWidth()/2 + ast.getWidth()/2;
		dispRadius = genRadius*0.3f; 
		
		ast.speedRotation = MathUtil.getRandom(MIN_ASTEROIDS_ROTATTION_SPEED, MAX_ASTEROIDS_ROTATTION_SPEED);
		
		shipX = world.getShip().getX();
		shipY = world.getShip().getY();
		
		delta = GeomUtil.getVector2(genRadius, world.getShip().getRotation());
		regionBorderAngle = world.getShip().getRotation() + 90; 
		
		astX = shipX + (int)delta.x;
		astY = shipY + (int)delta.y;
		
		disp = MathUtil.getRandSign() * dispRadius;
		delta = GeomUtil.getVector2(disp, regionBorderAngle);
		
		astX += delta.x;
		astY += delta.y;
		
		ast.setPosition(astX, astY);
		ast.setDirection(GeomUtil.getAngle(astX, astY, shipX, shipY));
		ast.speed = speed;
	}
	
	public void setState(EntityState state) {
		
		this.state = (AsteroidSingleGroupState) state;
		genTime = this.state.genTime;
	}
	
	@Override
	protected AsteroidSingle createNew() {
		
		AsteroidSingle ast = new AsteroidSingle();
		ast.create(world);
		
		return ast;
	}

	@Override
	public void reset() {
		super.reset();
		
		initGenTime = 0;
	}
}
