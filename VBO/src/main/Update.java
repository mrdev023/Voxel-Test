package main;
import math.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import blocks.*;


public class Update {
	
	private static float xDir,yDir,zDir;
	private static float speed = 0.01f;
	private static float xa = 0,ya = 0,za = 0;
	private static Block selectedBlock = null;
	private static Vector3f selectedVector = new Vector3f(0,0,0);
	
	/**
	 * @Info Fonction permettant de gerer les action de la souris
	 */
	public static void updateMouse(){
		
		Camera.getRotation().x -= Mouse.getDY() * 0.5;
		Camera.getRotation().y += Mouse.getDX() * 0.5;
		
		while(Mouse.next()){
			if(Mouse.getEventButtonState()){
				
			}else{
				
			}
		}
	}
	
	/**
	 * @Info Fonction permettant de gerer les action du clavier
	 */
	public static void updateKeyboard(){
		xDir = 0;
		yDir = 0;
		zDir = 0;
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
					Main.setRunning(false);
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_F2){
					Mouse.setGrabbed(!Mouse.isGrabbed());
				}
			}else{
				
			}
			
		}
		
		if(Camera.getRotation().x < -90) Camera.getRotation().x = -90;
		if(Camera.getRotation().x > 90) Camera.getRotation().x = 90;
		
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			zDir = -speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			zDir = speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			xDir = -speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			xDir = speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
//			if(grounded)yDir = 0.3f;
//			if(!gravity)yDir = speed;
			yDir = speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			speed = 0.05f;
		}else{
			speed = 0.01f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
//			if(!gravity)yDir = -speed;
			yDir = -speed;
		}
		
		xa += xDir * Math.cos(Math.toRadians(Camera.getRotation().y)) - zDir * Math.sin(Math.toRadians(Camera.getRotation().y));
		ya += yDir;
		za += zDir * Math.cos(Math.toRadians(Camera.getRotation().y)) + xDir * Math.sin(Math.toRadians(Camera.getRotation().y));
		
		Camera.move(xa,ya,za);
		
		xa *= 0.9f;
		ya *= 0.9f;
		za *= 0.9f;
		
		if(Display.isCloseRequested()){
			Main.setRunning(false);
		}
	}
	
	/**
	 * @Info Fonction de mettre a jour le display et d'autre choses predefinie
	 */
	public static void update(){
		Main.getGame().update();
		Camera.getPlayerRaycast().update();
		if(Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()) != null){
			boolean nx = false, ny = false, nz = false;
			if(Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).x < 0)nx = true;
			if(Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).y < 0)ny = true;
			if(Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).z < 0)nz = true;
			selectedBlock = Main.getGame().getWorld().getBlock((int)Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).getX(), (int)Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).getY(), (int)Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).getZ(),nx,ny,nz);
			selectedVector = new Vector3f(Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).getX(), Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).getY(), Camera.getPlayerRaycast().getBlock(Main.getGame().getWorld()).getZ());
		}else{
			selectedBlock = null;
			selectedVector = null;
		}
	}

	public static float getxDir() {
		return xDir;
	}

	public static void setxDir(float xDir) {
		Update.xDir = xDir;
	}

	public static float getyDir() {
		return yDir;
	}

	public static void setyDir(float yDir) {
		Update.yDir = yDir;
	}

	public static float getzDir() {
		return zDir;
	}

	public static void setzDir(float zDir) {
		Update.zDir = zDir;
	}

	public static float getSpeed() {
		return speed;
	}

	public static void setSpeed(float speed) {
		Update.speed = speed;
	}

	public static float getXa() {
		return xa;
	}

	public static void setXa(float xa) {
		Update.xa = xa;
	}

	public static float getYa() {
		return ya;
	}

	public static void setYa(float ya) {
		Update.ya = ya;
	}

	public static float getZa() {
		return za;
	}

	public static void setZa(float za) {
		Update.za = za;
	}

	public static Block getSelectedBlock() {
		return selectedBlock;
	}

	public static void setSelectedBlock(Block selectedBlock) {
		Update.selectedBlock = selectedBlock;
	}

	public static Vector3f getSelectedVector() {
		return selectedVector;
	}

	public static void setSelectedVector(Vector3f selectedVector) {
		Update.selectedVector = selectedVector;
	}
	
	
	
}
