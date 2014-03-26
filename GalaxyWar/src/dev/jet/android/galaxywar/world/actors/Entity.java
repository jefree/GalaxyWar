package dev.jet.android.galaxywar.world.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dev.jet.android.galaxywar.media.Picture;
import dev.jet.android.galaxywar.world.World;

public class Entity extends Actor {
	
	boolean enable;
	int speed;
	float life;
	protected World world;
	
	protected Picture picture;
	private Rectangle rectangle;
	
	public void create(World _world, Picture _picture ) {
		
		world = _world;
		picture = _picture;
		
		setWidth(picture.getWidth());
		setHeight(picture.getHeight());
		
		setOrigin(getWidth()/2, getHeight()/2);
		
		rectangle = new Rectangle();
		
		reboot();
	}
	
	public Rectangle getRectangle() {
		
		rectangle.setX(getX() - getWidth()/2);
		rectangle.setY(getY() - getHeight()/2);
		rectangle.setWidth(getWidth());
		rectangle.setHeight(getHeight());
		
		return rectangle;
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
		picture.draw(this, batch, alphaParent);
	}
}
