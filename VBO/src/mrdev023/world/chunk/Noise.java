package mrdev023.world.chunk;

import java.util.*;

import mrdev023.math.*;

public class Noise {

	private long seed;
	private Random rand;
	private int octave;
	private float amplitude;
	
	public Noise(long seed,int octave, float amplitude){
		this.seed = seed;
		this.octave = octave;
		this.amplitude = amplitude;
		
		rand = new Random();
	}
	
	public float getNoise(float x,float z){
		int xmin = 0,xmax = 0,zmin = 0,zmax = 0;
		if(x<0){
			xmin = (int)(double)x/octave;
			xmax = (int) xmin - 1;			
		}else{
			xmin = (int)(double)x/octave;
			xmax = (int) xmin + 1;			
		}
		
		if(z<0){
			zmin = (int)(double)z/octave;
			zmax = (int) zmin - 1;
		}else{
			zmin = (int)(double)z/octave;
			zmax = (int) zmin + 1;
		}

		
		Vector2f a = new Vector2f(xmin,zmin);
		Vector2f b = new Vector2f(xmax,zmin);
		Vector2f c = new Vector2f(xmax,zmax);
		Vector2f d = new Vector2f(xmin,zmax);
		
		float ra = (float)noise(a);
		float rb = (float)noise(b);
		float rc = (float)noise(c);
		float rd = (float)noise(d);
		
		float t = (x - xmin * octave) / octave;
		
		float i1 = interpolate(ra,rb,t);		
		float i2 = interpolate(rd,rc,t);
		
		float h = interpolate(i1,i2,(z - zmin * octave) / octave);
		return h * amplitude;
	}
	
	private float interpolate(float a, float b, float t){
		float ft = (float) (t * Math.PI);
		float f = (float )((1f - Math.cos(ft)) * 0.5f);
		float ret = a * ( 1f - f) + b * f;
		
		return ret;
	}
	
	private double noise(Vector2f coord){
		double var = 10000 * (Math.sin(coord.getX() + Math.cos(coord.getY())) + Math.tan(seed));
		rand.setSeed((long)var);
		double ret = rand.nextDouble();
		
		return ret;
	}
}
