package mrdev023.game;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import mrdev023.game.gamestate.*;
import mrdev023.gameengine.*;
import mrdev023.math.*;
import mrdev023.rendering.*;
import mrdev023.rendering.gui.*;
import mrdev023.rendering.gui.action.*;
import mrdev023.world.*;

public class OptionMenu implements GameInterface {

	public ArrayList<GUI> guiList = new ArrayList<GUI>();
	public DisplayMode[] availableDisplayMode;
	public int selectDisplayMode = 0;
	public GUI displayRender,fullscreenRender;
	public boolean IsFullscreen;

	public OptionMenu() {}

	public void init() {
		guiList.clear();
		availableDisplayMode = DisplayManager.getDisplayModeArray();
		IsFullscreen = Display.isFullscreen();
		displayRender = new ButtonGUI(Display.getDisplayMode().getWidth() + "x"
				+ Display.getDisplayMode().getHeight() + ":"
				+ Display.getDisplayMode().getFrequency() + ":"
				+ Display.getDisplayMode().getBitsPerPixel(),0.35f,0.3f,0.3f,0.075f).setAction(
				new Action() {
					public void manageAction(int action) {

					}

					public void hover(Vector2f position) {
					}
				}).setHoverAnimation(false);
		guiList.add(new ButtonGUI("Back",0.2f,0.8f,0.2f,0.075f).setAction(new Action() {
					public void manageAction(int action) {
						if (action == GUI.CLICKED)
							GameEngine.changeGameState(GameState.MAIN_MENU);
					}

					public void hover(Vector2f position) {
					}
				}));
		guiList.add(new ButtonGUI("Apply",0.6f,0.8f,0.2f,0.075f).setAction(new Action() {
					public void manageAction(int action) {
						try {
							Display.setDisplayMode(availableDisplayMode[selectDisplayMode]);
							Display.setFullscreen(IsFullscreen);
							DisplayManager.updateDisplay();
						} catch (LWJGLException e) {}
					}

					public void hover(Vector2f position) {
					}
				}));
		guiList.add(new ButtonGUI("<",0.1f,0.3f,0.2f,0.075f).setAction(new Action() {
					public void manageAction(int action) {
						if (action == GUI.CLICKED) {
							selectDisplayMode--;
							if (selectDisplayMode > availableDisplayMode.length - 1)
								selectDisplayMode = 0;
							if (selectDisplayMode < 0)
								selectDisplayMode = availableDisplayMode.length - 1;
							displayRender.setValue(availableDisplayMode[selectDisplayMode].getWidth()
									+ "x"
									+ availableDisplayMode[selectDisplayMode].getHeight()
									+ ":"
									+ availableDisplayMode[selectDisplayMode].getFrequency()
									+ ":" + availableDisplayMode[selectDisplayMode].getBitsPerPixel() + " (" + (selectDisplayMode+1) + "/" + availableDisplayMode.length + ")");
						}
					}

					public void hover(Vector2f position) {
					}
				}));
		guiList.add(new ButtonGUI(">",0.7f,0.3f,0.2f,0.075f).setAction(new Action() {
					public void manageAction(int action) {
						if (action == GUI.CLICKED) {
							selectDisplayMode++;
							if (selectDisplayMode > availableDisplayMode.length - 1)
								selectDisplayMode = 0;
							if (selectDisplayMode < 0)
								selectDisplayMode = availableDisplayMode.length - 1;
							displayRender.setValue(availableDisplayMode[selectDisplayMode].getWidth()
									+ "x"
									+ availableDisplayMode[selectDisplayMode].getHeight()
									+ ":"
									+ availableDisplayMode[selectDisplayMode].getFrequency()
									+ ":" + availableDisplayMode[selectDisplayMode].getBitsPerPixel());
						}
					}

					public void hover(Vector2f position) {
					}
				}));
		guiList.add(displayRender);
		guiList.add((fullscreenRender = new ButtonGUI("Fullscreen: " + Display.isFullscreen(),0.4f,0.4f,0.2f,0.075f).setAction(
				new Action() {
					public void manageAction(int action) {
						IsFullscreen = !IsFullscreen;
						fullscreenRender.setValue("Fullscreen: " + IsFullscreen);
					}

					public void hover(Vector2f position) {
					}
				})));
		guiList.add(new LabelGUI("Option", 0.34f,0.1f,0.3f,0.10f));		
	}
	
	public void render() {

	}
	
	public void updateGUI() {
		for(GUI gui : guiList){
			gui.updateGUI();
		}
	}

	public void update() {
		for (GUI gui : guiList) {
			gui.updateAction();
		}
	}

	public void renderGUI() {
		glColor3f(0, 0, 0);
		glPointSize(5);
		glBegin(GL_POINTS);
		glVertex2f(Mouse.getX(),
				Display.getDisplayMode().getHeight() - Mouse.getY());
		glEnd();
		for (GUI gui : guiList) {
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


}
