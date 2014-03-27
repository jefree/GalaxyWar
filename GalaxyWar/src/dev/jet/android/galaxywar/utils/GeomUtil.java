package dev.jet.android.galaxywar.utils;

import com.badlogic.gdx.math.Vector2;

public class GeomUtil {

	public static Vector2 midPoint (Vector2 v1, Vector2 v2) {		
		return new Vector2((v1.x + v2.x)/2, (v1.y + v2.y)/2);
	}
	
	public static float getDistance(float x1, float y1, float x2, float y2) {
		
		return (float) Math.sqrt( Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2) );
	}
	
	public static float getAngle(float x1, float y1, float x2, float y2) {
		
		float dx = x2-x1;
		float dy = y2-y1;
		
		double ang = Math.atan2(dy, dx);
		
		return (float)Math.toDegrees(ang) - 90;
	}
	
	public static float[] getSides(float hypotenuse, float angle) {
		
		angle = (float)Math.toRadians(angle);
		float []sides = { -(float)Math.sin(angle) * hypotenuse, 
		        (float)Math.cos(angle) * hypotenuse};
		
		return sides;
	}
	
}
