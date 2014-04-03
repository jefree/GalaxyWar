package dev.jet.android.galaxywar.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ScreenUtil {

	public static void centered (Actor child, Actor parent, float dx, float dy) {
		
		child.setPosition(parent.getX() + parent.getWidth()/2 - child.getWidth()/2 + dx, 
				parent.getY() + parent.getHeight()/2 - child.getHeight()/2 + dy);
	}
}
