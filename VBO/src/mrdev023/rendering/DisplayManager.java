package mrdev023.rendering;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import mrdev023.gameEngine.*;
import mrdev023.update.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;


public class DisplayManager {

	private static long delta = 0;
	private static float fov = 45;
	
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
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0, Display.getDisplayMode().getWidth(), Display
				.getDisplayMode().getHeight(), 0);
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
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
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
	
}
