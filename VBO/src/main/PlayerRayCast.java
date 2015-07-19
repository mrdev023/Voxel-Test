package main;

import java.util.*;

import math.*;
import world.*;

public class PlayerRayCast {

	private List<Vector3f> points;
	private int length = 10;
	private int precision = 16;
	
	public PlayerRayCast(){
		this.points = new ArrayList<Vector3f>();
		for(int i = 0; i  < length * precision; i++){
			points.add(new Vector3f());
		}
	}
	
	public void update(){
		int i = 0;
		for(Vector3f v : points){
			Vector3f pos = Camera.getPosition().copy().add(0f,Camera.getHeight(),0f).add(Camera.getDirection().copy().mul(i/(float)precision));
			v.set(pos);
			i++;
		}
	}
	
	public Vector3f getBlock(World world){
		for(Vector3f v : points){
			boolean nx = false, ny = false, nz = false;
			if(v.x < 0)nx = true;
			if(v.y < 0)ny = true;
			if(v.z < 0)nz = true;
			boolean  block = world.getBlock((int)v.x, (int)v.y, (int)v.z,nx,ny,nz) != null;
			
			if(block) return new Vector3f(v.x, v.y,v.z);
		}
		return null;
	}
	
}
