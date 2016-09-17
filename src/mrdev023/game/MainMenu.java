package mrdev023.game;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.util.*;

import mrdev023.audio.*;
import mrdev023.game.gamestate.*;
import mrdev023.gameengine.*;
import mrdev023.math.*;
import mrdev023.rendering.gui.*;
import mrdev023.rendering.gui.action.*;
import mrdev023.world.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

public class MainMenu implements GameInterface{
	
	public ArrayList<GUI> guiList = new ArrayList<GUI>();
	
	public MainMenu(){}
	
	public void init(){
		GUI button = new ButtonGUI("SOLO",0.4f,0.3f,0.2f,0.075f);
		button.setAction(new Action() {
			public void manageAction(int action) {
				if(action == GUI.CLICKED)GameEngine.changeGameState(GameState.SOLO_GAME);
			}
			
			public void hover(Vector2f position) {}
		});
		guiList.add(button);
		button = new ButtonGUI("OPTION",0.4f,0.4f,0.2f,0.075f);
		button.setAction(new Action() {
			public void manageAction(int action) {
				if(action == GUI.CLICKED)GameEngine.changeGameState(GameState.OPTION_MENU);
			}
			
			public void hover(Vector2f position) {}
		});
		guiList.add(button);
		button = new ButtonGUI("ABOUT",0.4f,0.5f,0.2f,0.075f);
		button.setAction(new Action() {
			public void manageAction(int action) {
				if(action == GUI.CLICKED)GameEngine.changeGameState(GameState.ABOUT_MENU);
			}
			
			public void hover(Vector2f position) {}
		});
		guiList.add(button);
		button = new ButtonGUI("EXIT",0.4f,0.6f,0.2f,0.075f);
		button.setAction(new Action() {
			public void manageAction(int action) {
				if(action == GUI.CLICKED)GameEngine.destroy();
			}
			
			public void hover(Vector2f position) {}
		});
		guiList.add(button);
		guiList.add(new LabelGUI("Test Voxel v0.1", 0.34f,0.1f,0.3f,0.10f));
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
	}

	public void updateGUI() {
		for(GUI gui : guiList){
			gui.updateGUI();
		}
	}

}
