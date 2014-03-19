package dev.jet.android.galaxywar.utils;

public class UtilityMath {
	
	public static float getRandom(float max) {
		return getRandom(0, max);
	}
	
	public static float getRandom(float min, float max, boolean negative) {
		
		int sign = 1;
		
		if (negative){
			sign = (int)Math.pow(-1, (int)(Math.random()/0.5));
		}
		
		return sign*(min + (float)Math.random()*(max-min));
		
	}
	
	public static float getRandom(float min, float max) {
		
		return getRandom(min, max, false);
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
