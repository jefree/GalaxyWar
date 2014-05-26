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
	
	private static long nextID = 0;
	
	public static void debug(boolean debug) {
		Entity.debug = debug;
		
		if (debug && debugRenderer == null) {
			debugRenderer = new ShapeRenderer();
		}
	}
	
	public float speed;
	public float life;
	public boolean enable;
	
	public Vector2 direction;
	
	public BaseWorld world;
	
	public final long ID;
	protected TextureRegion image;
	private Circle circle;
	
	public abstract void setState(EntityState state);
	
	public Entity() {
		
		this.ID = nextID;
		nextID += 1;
	}
	
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
	
	public void setDirection(float degrees) {
		this.direction = GeomUtil.getVector2(1, degrees);
	}
	
	public float getDirAngle(){
		return GeomUtil.getAngle(0, 0,direction.x, direction.y);
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
	public String toString() {
		return "Entity: " + ID;
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
