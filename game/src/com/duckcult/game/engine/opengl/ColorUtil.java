package com.duckcult.game.engine.opengl;

import android.graphics.Color;

public class ColorUtil {
	public static float redIntensity(int color){
		return ((float)(Color.red(color)))/255;
	}
	
	public static float greenIntensity(int color){
		return ((float)Color.green(color))/255;
	}
	
	public static float blueIntensity(int color){
		return ((float)Color.blue(color))/255;
	}
	
	public static float alphaIntensity(int color){
		return ((float)Color.alpha(color))/255;
	}
	
	public static int intensityToInt(float redIntensity, float greenIntensity, float blueIntensity, float alphaIntensity){
		int r = (int) (redIntensity >= 0f && redIntensity <= 1.0f ? (redIntensity*255) : 0);
		int g = (int) (greenIntensity >= 0f && greenIntensity <= 1.0f ? (greenIntensity*255) : 0);
		int b = (int) (blueIntensity >= 0f && blueIntensity <= 1.0f ? (blueIntensity*255) : 0);
		int a = (int) (alphaIntensity >= 0f && alphaIntensity <= 1.0f ? (alphaIntensity*255) : 0);
		return Color.argb(a, r, g, b);
	}
	
	public static int intensityToInt(float redIntensity, float greenIntensity, float blueIntensity){
		int r = (int) (redIntensity >= 0f && redIntensity <= 1.0f ? (redIntensity*255) : 0);
		int g = (int) (greenIntensity >= 0f && greenIntensity <= 1.0f ? (greenIntensity*255) : 0);
		int b = (int) (blueIntensity >= 0f && blueIntensity <= 1.0f ? (blueIntensity*255) : 0);
		return Color.rgb(r, g, b);
	}
}
