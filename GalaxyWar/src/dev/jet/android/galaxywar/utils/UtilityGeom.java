package dev.jet.android.galaxywar.utils;

import com.badlogic.gdx.math.Vector2;

public class UtilityGeom {

	public static Vector2 midPoint (Vector2 v1, Vector2 v2) {		
		return new Vector2((v1.x + v2.x)/2, (v1.y + v2.y)/2);
	}
	
}
