package dev.jet.android.galaxywar.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ScreenUtil {

	public static void centered (Actor child, Actor parent, float dx, float dy) {
		
		centeredX(child, parent, 0, 0);
		centeredY(child, parent, 0, 0);
		
		child.translate(dx, dy);
	}
	
	public static void centeredX (Actor child, Actor parent, float dx, float dy) {
		child.setX(parent.getX() + parent.getWidth()/2 - child.getWidth()/2 + dx);
		child.translate(dx, dy);
	}
	
	public static void centeredY (Actor child, Actor parent, float dx, float dy) {
		child.setY(parent.getY() + parent.getHeight()/2 - child.getHeight()/2);
		child.translate(dx, dy);
	}
	
	public static void top(Actor child, Actor parent, float dx, float dy) {
		child.setY(parent.getHeight());
		child.translate(dx, dy);
	}
	
	public static void right(Actor child, Actor parent, float dx, float dy) {
		child.setX(parent.getWidth());
		child.translate(dx, dy);
	}
	
	public static void centerTop(Actor child, Actor parent, float dx, float dy) {
		centeredX(child, parent, 0, 0);
		top(child, parent, 0 , 0);
		child.translate(dx, dy);
	}
	
	
	
}

