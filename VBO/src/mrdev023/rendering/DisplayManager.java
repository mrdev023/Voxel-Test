package mrdev023.rendering;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import java.util.*;

import mrdev023.gameengine.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;


public class DisplayManager {

	private static long delta = 0;
	private static float fov = 45;
	private static int height = GameEngine.getHeight(),width = GameEngine.getWidth();
	
	/**
	 * @Info Nettoie l'ecran
	 */
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0.19f, 0.79f, 0.82f, 1);
	}
	
	/**
	 * @Info Definie le mode d'affichage pour le rendu en 3d
	 */
	public static void preRender3D(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, (float)Display.getDisplayMode().getWidth()/(float)Display.getDisplayMode().getHeight(), 0.1f, 1000f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_FRONT);
		glEnable(GL_TEXTURE_2D);
	}
	
	/**
	 * @Info Fait le rendu 3d
	 */
	public static void render3D(){
		GameEngine.getGameState().render();
	}

	/**
	 * @Info Definie le mode d'affichage pour le rendu en 2d
	 */
	public static void preRender2D(){
		glDisable(GL_CULL_FACE);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,Display.getDisplayMode().getWidth(),Display.getDisplayMode().getHeight(),0,-1,1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	/**
	 * @Info Fait le rendu 2d
	 */
	public static void render2D(){
		GameEngine.getGameState().renderGUI();
	}

	/**
	 * @Info mets a jour la resolution de l'ecran
	 */
	public static void updateDisplay() {
		width = Display.getDisplayMode().getWidth();
		height = Display.getDisplayMode().getHeight();
		glViewport(0, 0, width,height);
		GameEngine.gameState.updateGUI();
	}
	
	public static DisplayMode[] getDisplayModeArray(){
		try {
			ArrayList<DisplayMode> dl = new ArrayList<DisplayMode>();
			for(DisplayMode d : Display.getAvailableDisplayModes()){
				if(d.getBitsPerPixel() == 32 && d.getFrequency() == 60){
					dl.add(d);
				}
			}
			DisplayMode[] dl2 = new DisplayMode[dl.size()];
			for(int i = 0;i < dl2.length;i++){
				dl2[i] = dl.get(i);
			}
			dl = null;
			return dl2;
		} catch (LWJGLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static long getDelta() {
		return delta;
	}

	public static void setDelta(long delta) {
		DisplayManager.delta = delta;
	}

	public static float getFov() {
		return fov;
	}

	public static void setFov(float fov) {
		DisplayManager.fov = fov;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		DisplayManager.height = height;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		DisplayManager.width = width;
	}
	
	
	
}
