package main;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;


public class Update {
	
	private static float xDir,yDir,zDir;
	private static float speed = 0.01f;
	private static float xa = 0,ya = 0,za = 0;
	
	
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
		Display.update();
	}
	
}
