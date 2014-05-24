package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dev.jet.android.galaxywar.utils.GeomUtil;
import dev.jet.android.galaxywar.world.BaseWorld;
import dev.jet.android.galaxywar.world.EntityState;

public abstract class Entity extends Actor {
	
	static ShapeRenderer debugRenderer;
	static boolean debug;
	
	public static void debug(boolean debug) {
		Entity.debug = debug;
		
		if (debug && debugRenderer == null) {
			debugRenderer = new ShapeRenderer();
		}
	}
	
	protected int speed;
	protected float life;
	protected boolean enable;
	
	protected BaseWorld world;
	
	protected TextureRegion image;
	private Circle circle;
	
	private Vector2 direction;
	
	public abstract void setState(EntityState state);
	
	public void create(BaseWorld world, TextureRegion image) {
		
		this.create(world);
		setImage(image);
	}
	
	public void create(BaseWorld world) {
		
		this.world = world;
		enable = true;
		
		circle = new Circle();
		
		direction = new Vector2();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		moveBy(direction.x * speed * delta, direction.y * speed * delta);
	}
	
	public Circle getCircle() {
		
		circle.setX(getX());
		circle.setY(getY());
		
		circle.setRadius(image.getRegionWidth()/2);
		
		return circle;
	}
	
	public Vector2 getCenter() {
		return new Vector2(circle.x, circle.y);
	}
	
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	
	public void setDirection(float degrees) {
		this.direction = GeomUtil.getVector2(1, degrees);
	}
	
	public Vector2 getDirection() {
		return direction;
	}
	
	public float getDirAngle(){
		
		return GeomUtil.getAngle(0, 0,direction.x, direction.y);
		
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}
	
	public boolean isEnable() {
		return enable;
	}
	
	public float getScreenX() {
		return getX() + world.getOffsetX();
	}
	
	public float getScreenY() {
		return getY() + world.getOffsetY();
	}
	
	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}
	
	public void setImage(TextureRegion image) {
		
		this.image = image;
		
		setWidth(image.getRegionWidth());
		setHeight(image.getRegionHeight());
	
		setOrigin(getWidth()/2, getHeight()/2);
	}
	
	public boolean collide (Entity e) {
		return e.getCircle().overlaps(this.getCircle());
	}
	
	public void reset() {
		enable = true;
	}
	
	public void destroy() {
		enable = false;
		remove();
	}
	
	@Override
	public void draw(Batch batch, float alphaParent) {
		
		batch.draw(image, getScreenX() - getWidth()/2, getScreenY() - getHeight()/2, 
				getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(), 
                getRotation());
		
		if (debug) {
			
			batch.end();
			
			debugRenderer.begin(ShapeType.Line);
			
			debugRenderer.arc(getScreenX(), getScreenY(), 
					circle.radius, 0, 360);
			
			debugRenderer.end();
			
			batch.begin();
		}
		
	}
}
