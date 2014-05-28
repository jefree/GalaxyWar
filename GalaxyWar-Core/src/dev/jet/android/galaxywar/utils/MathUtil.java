package dev.jet.android.galaxywar.utils;

public class MathUtil {
	
	public static int getRandSign() {
		return (int)Math.pow(-1, (int)(Math.random()/0.5));
	}
	
	public static float getRandom(float max) {
		return getRandom(0, max);
	}
	
	public static float getRandom(float min, float max, boolean negative) {
		
		int sign = 1;
		
		if (negative){
			sign = getRandSign();
		}
		
		return sign*(min + (float)Math.random()*(max-min));
		
	}
	
	public static float getRandom(float min, float max) {
		
		return getRandom(min, max, false);
	}
	
	public static float toSeconds(float minutes, float seconds) {
		return minutes*60 + seconds;
	}
	

}
