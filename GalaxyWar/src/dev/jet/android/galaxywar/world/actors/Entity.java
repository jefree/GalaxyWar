package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dev.jet.android.galaxywar.media.Picture;
import dev.jet.android.galaxywar.world.World;

public class Entity extends Actor {
	
	boolean enable;
	int speed;
	
	protected World world;
	
	protected Picture picture;
	private Rectangle rectangle;
	
	public void create(World _world, Picture _picture ) {
		
		world = _world;
		picture = _picture;
		
		enable = false;
		
		setWidth(picture.getWidth());
		setHeight(picture.getHeight());
		
		setOrigin(getWidth()/2, getHeight()/2);
		
		rectangle = new Rectangle();
	}
	
	public Rectangle getRectangle() {
		
		rectangle.setX(getX() - getWidth()/2);
		rectangle.setY(getY() - getHeight()/2);
		rectangle.setWidth(getWidth());
		rectangle.setHeight(getHeight());
		
		return rectangle;
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
	
	public float getScreenX() {
		return getX() + world.getOffsetX();
	}
	
	public float getScreenY() {
		return getY() + world.getOffsetY();
	}
	
	public boolean collide (Entity e) {
		
		Rectangle own = getRectangle();
		Rectangle other = e.getRectangle();
		
		return own.overlaps(other);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		picture.update();
	}
	
	@Override
	public void draw(SpriteBatch batch, float alphaParent) {
		picture.draw(this, batch, alphaParent);
	}
}
