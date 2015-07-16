package world;

import java.util.*;

public class WorldNoise {

	public Noise noise;
	public Noise colorVariationNoise;
	public Random random;
	
	public WorldNoise(long seed){
		noise = new Noise(seed, 20, 5);
		colorVariationNoise = new Noise(seed, 40, 2);
		random = new Random(seed);
	}
	
}
