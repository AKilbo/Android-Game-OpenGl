package com.duckcult.game.engine;

/**
 * A class designed to hold constants for FPS constraints in the engine.
 * They are mainly used within the MainThread class to maintain a constant game speed but the class also
 * contains helper functions to translate between numbers in units per second to units per update and vice versa.
 * This allows us to tweak game performance from a single source.
 * @author eharpste
 *
 */
public class FPSConstraints {
	/**
	 * Target maximum frames per second
	 */
	public static final int MAX_FPS = 50;
	/**
	 * Number of frames that are allowed to be skipped to catch up on model updates. 
	 */
	public static final int MAX_FRAME_SKIPS = 5;
	/**
	 * The period (in milliseconds) that an update cycle should take. 
	 */
	public static final int FRAME_PERIOD = 1000 / MAX_FPS;
	
	/**
	 * Converts an integer from units per second to units per update
	 * @param perSecond	an integer of units per second
	 * @return the equivalent of units per update
	 */
	public static int PStoPu (int perSecond){
		return perSecond/FRAME_PERIOD;
	}
	
	/**
	 * Converts an integer from units per update to units per second
	 * @param perSecond	an integer of units per update
	 * @return the equivalent of units per second
	 */
	public static int PUtoPS (int perUpdate) {
		return perUpdate*FRAME_PERIOD;
	}
	
	/**
	 * Converts a float from units per second to units per update
	 * @param perSecond	a float of units per second
	 * @return the equivalent of units per update
	 */
	public static float PStoPU(float perSecond) {
		return perSecond/FRAME_PERIOD;
	}
	
	/**
	 * Converts a float from units per update to units per second
	 * @param perSecond	a float of units per update
	 * @return the equivalent of units per second
	 */
	public static float PUtoPS(float perUpdate) {
		return perUpdate*FRAME_PERIOD;
	}
	
	/**
	 * Converts a double from units per second to units per update
	 * @param perSecond	a double of units per second
	 * @return the equivalent of units per update
	 */
	public static double PStoPU(double perSecond) {
		return perSecond/FRAME_PERIOD;
	}
	
	/**
	 * Converts a double from units per update to units per second
	 * @param perSecond	a double of units per update
	 * @return the equivalent of units per second
	 */
	public static double PUtoPS(double perUpdate) {
		return perUpdate*FRAME_PERIOD;
	}
}
