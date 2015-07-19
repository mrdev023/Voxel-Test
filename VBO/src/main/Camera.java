package main;

import static org.lwjgl.opengl.GL11.*;
import world.*;
import blocks.*;
import math.*;

public class Camera {
	
	private static Vector3f position = new Vector3f(0,4,0);
	private static Vector2f rotation = new Vector2f(0,120);
	private static PlayerRayCast playerRaycast;
	private static final float HEIGHT = (float) (1.5f - (Block.getSize()/2.0f));
	
	public static void initCamera(){
		playerRaycast = new PlayerRayCast();
	}
	
	public static void renderCamera(){
		glLoadIdentity();
		glRotatef(rotation.getX(), 1, 0, 0);
		glRotatef(rotation.getY(), 0, 1, 0);
		glTranslatef(-position.getX(), -(position.getY()+getHeight()), -position.getZ());
	}

	public static void move(float xa,float ya,float za){
//		if(true){
//			gravityFactor += World.GRAVITY * 0.01f;
//			if(grounded) gravityFactor = 0;
//			ya -= gravityFactor;
//		}else{
//			gravityFactor = 0;
//		}
		int xStep = (int)Math.abs(xa * 100);
		for(int i = 0; i < xStep; i++){
//			if(!isColliding(xa/xStep, 0, 0) || noClip){
				position.x += xa/xStep;
//			}else{
//				xa = 0;
//			}
		}
		int yStep = (int)Math.abs(ya * 100);
		for(int i = 0; i < yStep; i++){
//			if(!isColliding(0, ya/yStep, 0) || noClip){
				position.y += ya/yStep;
//			}else{
//				ya = 0;
//			}
		}
		int zStep = (int)Math.abs(za * 100);
		for(int i = 0; i < zStep; i++){
//			if(!isColliding(0, 0, za/zStep) || noClip){
				position.z += za/zStep;
//			}else{
//				za = 0;
//			}
		}
	}
	
	public static Vector3f getDirection(){
		Vector3f r = new Vector3f();
		
		float cosY = (float)Math.cos(Math.toRadians(rotation.getY() - 90));
		float sinY = (float)Math.sin(Math.toRadians(rotation.getY() - 90));
		float cosP = (float)Math.cos(Math.toRadians(-rotation.getX()));
		float sinP = (float)Math.sin(Math.toRadians(-rotation.getX()));
		
		r.setX(cosY * cosP);
		r.setY(sinP);
		r.setZ(sinY * cosP);
		
		r.normalize();
		
		return r;
	}
	
	public static Vector3f getPosition() {
		return position;
	}

	public static void setPosition(Vector3f position) {
		Camera.position = position;
	}

	public static Vector2f getRotation() {
		return rotation;
	}

	public static void setRotation(Vector2f rotation) {
		Camera.rotation = rotation;
	}

	public static float getHeight() {
		return HEIGHT;
	}

	public static PlayerRayCast getPlayerRaycast() {
		return playerRaycast;
	}

	public static void setPlayerRaycast(PlayerRayCast playerRaycast) {
		Camera.playerRaycast = playerRaycast;
	}
	
	
	
}
