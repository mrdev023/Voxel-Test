package mrdev023.game;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import mrdev023.game.gamestate.*;
import mrdev023.gameEngine.*;
import mrdev023.math.*;
import mrdev023.rendering.gui.*;
import mrdev023.rendering.gui.action.*;
import mrdev023.world.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

public class MainMenu implements GameInterface{
	
	public ArrayList<GUI> guiList = new ArrayList<GUI>();	
	
	public MainMenu(){
		GUI button = new ButtonGUI("SOLO",Display.getDisplayMode().getWidth()/2 - 100,Display.getDisplayMode().getHeight()/2 - 80,200,60);
		button.setAction(new Action() {
			public void manageAction(int action) {
				if(action == GUI.CLICKED)GameEngine.changeGameState(GameState.SOLO_GAME);
			}
			
			public void hover(Vector2f position) {}
		});
		guiList.add(button);
		guiList.add(new LabelGUI("Test Voxel v0.1", Display.getDisplayMode().getWidth()/2 - 100, Display.getDisplayMode().getHeight()/2 - 300, 200, 100));
	}

	public void render() {
		
	}

	public void update() {
		for(GUI gui : guiList){
			gui.updateAction();
		}
		
		
	}

	public void renderGUI() {
		glColor3f(0,0,0);
		glPointSize(5);
		glBegin(GL_POINTS);
		glVertex2f(Mouse.getX(),Display.getDisplayMode().getHeight() - Mouse.getY());
		glEnd();
		for(GUI gui : guiList){
			gui.render();
		}
	}
	
	public void destroyGameState() {

	}

	public World getWorld() {
		return null;
	}

	public void updateMouse() {
		
	}

	public void updateKeyboard() {
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
					GameEngine.setRunning(false);
				}
			}else{

			}
		}
	}

}
