package mrdev023.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import mrdev023.blocks.*;
import mrdev023.game.gamestate.*;
import mrdev023.gameEngine.*;
import mrdev023.math.*;
import mrdev023.rendering.*;
import mrdev023.update.*;
import mrdev023.world.*;


public class SoloGame extends Game implements GameInterface{

	private static Block selectedBlock = null;
	private static Vector3f selectedVector = new Vector3f(0,0,0);
	
	public SoloGame() {
		super(new MultiWorld(0,120,50));
	}

	public void render(){
		Camera.renderCamera();
		this.world.render();
		if(selectedVector != null && selectedVector !=null){
			renderBlock((int)selectedVector.x,(int)selectedVector.y,(int)selectedVector.z);
		}
	}
	
	public void update(){
		Camera.getPlayerRaycast().update();
		if(GameEngine.getGameState().getWorld() != null){
			if(Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()) != null){
				boolean nx = false, ny = false, nz = false;
				if(Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).x < 0)nx = true;
				if(Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).y < 0)ny = true;
				if(Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).z < 0)nz = true;
				selectedBlock = GameEngine.getGameState().getWorld().getBlock((int)Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).getX(), (int)Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).getY(), (int)Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).getZ(),nx,ny,nz);
				selectedVector = new Vector3f(Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).getX(), Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).getY(), Camera.getPlayerRaycast().getBlock(GameEngine.getGameState().getWorld()).getZ());
			}else{
				selectedBlock = null;
				selectedVector = null;
			}
		}
		if(update >= 2){
			world.update();
			update = 0;
		}
		update++;
	}

	public void renderGUI() {
	
	}

	public void destroyGameState() {
		destroyGame();
	}
	
	private static void renderBlock(int x,int y ,int z){
		float s = 1;
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glDisable(GL_CULL_FACE);
		glLineWidth(2);
		glBegin(GL_QUADS);
			glVertex3f(x,y,z);
			glVertex3f(x + s,y,z);
			glVertex3f(x + s, y + s, z);
			glVertex3f(x,y + s,z);
			
			glVertex3f(x + s,y,z + s);
			glVertex3f(x,y,z + s);
			glVertex3f(x,y + s,z + s);
			glVertex3f(x + s, y + s, z + s);

			glVertex3f(x + s,y,z);
			glVertex3f(x,y,z);
			glVertex3f(x,y,z + s);
			glVertex3f(x + s,y,z + s);
			
			glVertex3f(x,y + s,z);
			glVertex3f(x + s,y + s,z);
			glVertex3f(x + s,y + s,z + s);
			glVertex3f(x,y + s,z + s);
			
			glVertex3f(x,y,z);
			glVertex3f(x,y + s,z);
			glVertex3f(x,y + s,z + s);
			glVertex3f(x,y,z + s);
			
			glVertex3f(x + s,y + s,z);
			glVertex3f(x + s,y,z);
			glVertex3f(x + s,y,z + s);
			glVertex3f(x + s,y + s,z + s);
		glEnd();
		glEnable(GL_CULL_FACE);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}

	private static float xDir,yDir,zDir;
	private static float speed = 0.02f;
	private static float xa = 0,ya = 0,za = 0;
	
	public void updateMouse() {
		if(Mouse.isGrabbed()){
			
			Camera.getRotation().x -= Mouse.getDY() * 0.5;
			Camera.getRotation().y += Mouse.getDX() * 0.5;
			
			while(Mouse.next()){
				if(Mouse.getEventButtonState()){
					
				}else{
					
				}
			}
		}
		
	}

	public void updateKeyboard() {
		xDir = 0;
		yDir = 0;
		zDir = 0;
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
					GameEngine.setRunning(false);
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_F2){
					Mouse.setGrabbed(!Mouse.isGrabbed());
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_F5){
					Camera.setPosition(new Vector3f(0,2,0));
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_X){
					Camera.noClip = !Camera.noClip;
				}
			}else{
				
			}
			
		}
		
		if(Mouse.isGrabbed()){			
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
				if(Camera.grounded)yDir = 0.3f;
				if(!Camera.gravity)yDir = speed;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				speed = 0.01f;
			}else{
				speed = 0.02f;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
				if(!Camera.gravity)yDir = -speed;
			}
		}
		
		xa += xDir * Math.cos(Math.toRadians(Camera.getRotation().y)) - zDir * Math.sin(Math.toRadians(Camera.getRotation().y));
		ya += yDir;
		za += zDir * Math.cos(Math.toRadians(Camera.getRotation().y)) + xDir * Math.sin(Math.toRadians(Camera.getRotation().y));
		
		Camera.move(xa,ya,za);
		
		xa *= 0.9f;
		ya *= 0.9f;
		za *= 0.9f;
		
		if(Display.isCloseRequested()){
			GameEngine.setRunning(false);
		}
		
	}
}
