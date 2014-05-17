package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dev.jet.android.galaxywar.world.BaseWorld;

public abstract class Entity extends Actor {
	
	boolean enable;
	int speed;
	float life;
	protected BaseWorld world;
	
	protected TextureRegion image;
	private Rectangle rectangle;
	
	public void create(BaseWorld world, TextureRegion image) {
		
		this.create(world);
		setImage(image);
	}
	
	public void create(BaseWorld world) {
		
		this.world = world;
		rectangle = new Rectangle();
	}
	
	public Rectangle getRectangle() {
		
		rectangle.setX(getX() - getWidth()/2);
		rectangle.setY(getY() - getHeight()/2);
		rectangle.setWidth(getWidth());
		rectangle.setHeight(getHeight());
		
		return rectangle;
	}
	
	public Vector2 getCenter() {
		return getRectangle().getCenter(new Vector2());
	}
	
	public void reboot() {
		enable = false;
	}

	public void destroy() {
		enable = false;
		remove();
	}
	
	public void setEnable(boolean _enable) {
		enable = _enable;
	}
	
	public boolean isEnable() {
		return enable;
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

	public float getScreenX() {
		return getX() + world.getOffsetX();
	}
	
	public float getScreenY() {
		return getY() + world.getOffsetY();
	}
	
	public void setImage(TextureRegion image) {
		
		this.image = image;
		
		setWidth(image.getRegionWidth());
		setHeight(image.getRegionHeight());
	
		setOrigin(getWidth()/2, getHeight()/2);
	}
	
	public boolean collide (Entity e) {
		
		if (enable & e.isEnable()){
			
			Rectangle own = getRectangle();
			Rectangle other = e.getRectangle();
			
			return own.overlaps(other);
		}
		
		return false;
	}
	
	@Override
	public void draw(SpriteBatch batch, float alphaParent) {
		batch.draw(image, getScreenX() - getWidth()/2, getScreenY() - getHeight()/2, 
				getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(), 
                getRotation());
	}
}
